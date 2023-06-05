package engine;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.MaxCardsDrawnException;
import exceptions.UnallowedCardException;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.ChangeCard;
import model.cards.Changeable;
import model.cards.Color;
import model.cards.HitAndChangeCard;
import model.cards.HitCard;
import model.cards.NumberCard;
import model.cards.SkipCard;
import model.cards.WildCard;

public class Game {

	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private ArrayList<Card> discardPile;
	private int currentDrawn;
	private boolean playedCard;

	public Game(ArrayList<Player> players) {
		this.players = players;
		this.deck = new ArrayList<Card>();
		this.discardPile = new ArrayList<Card>();
		this.currentDrawn = 0;
		buildDeck();
		distribute();
		discardPile.add(deck.remove(deck.size() - 1));
	}

	private void buildDeck() {
		deck = new ArrayList<Card>();
		deck.add(new NumberCard(0, Color.GREEN));
		deck.add(new NumberCard(0, Color.RED));
		deck.add(new NumberCard(0, Color.BLUE));
		deck.add(new NumberCard(0, Color.YELLOW));

		for (int j = 0; j < 2; j++) {
			for (int i = 1; i <= 9; i++) {
				deck.add(new NumberCard(i, Color.GREEN));
				deck.add(new NumberCard(i, Color.RED));
				deck.add(new NumberCard(i, Color.BLUE));
				deck.add(new NumberCard(i, Color.YELLOW));
			}
			deck.add(new HitCard(Color.GREEN));
			deck.add(new HitCard(Color.RED));
			deck.add(new HitCard(Color.BLUE));
			deck.add(new HitCard(Color.YELLOW));

			deck.add(new SkipCard(Color.GREEN));
			deck.add(new SkipCard(Color.RED));
			deck.add(new SkipCard(Color.BLUE));
			deck.add(new SkipCard(Color.YELLOW));

			deck.add(new ChangeCard());
			deck.add(new HitAndChangeCard());
			deck.add(new ChangeCard());
			deck.add(new HitAndChangeCard());

		}
		Collections.shuffle(deck);
	}

	public void distribute() {
		for (Player p : players) {
			for (int i = 0; i < 7; i++) {
				p.getHand().add(deck.remove(0));
			}
		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	public int getCurrentDrawn() {
		return currentDrawn;
	}

	public Player getNextPlayer() {
		return players.get(1);
	}

	public Card getLatestDiscard() {
		return discardPile.get(discardPile.size() - 1);
	}

	public Player checkGameOver() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getHand().size() == 0)
				return players.get(i);
		}
		return null;
	}

	public void playCard(Card card) throws UnallowedCardException {
		Player currentPlayer = players.get(0);
		if (!currentPlayer.getHand().contains(card))
			throw new UnallowedCardException("You can only play a card from your hand.");
		if (card.getColor() != getLatestDiscard().getColor()) {
			if (card instanceof NumberCard && getLatestDiscard() instanceof NumberCard) {
				if ((((NumberCard) card).getNumber() != ((NumberCard) getLatestDiscard()).getNumber()))
					throw new UnallowedCardException();
			}
			else if(card instanceof NumberCard && ! (getLatestDiscard() instanceof NumberCard))
				throw new UnallowedCardException();
			else if ((card instanceof HitCard) && !(getLatestDiscard() instanceof HitCard))
				throw new UnallowedCardException();
			else if ((card instanceof SkipCard) && !(getLatestDiscard() instanceof SkipCard))
				throw new UnallowedCardException();
			
		}
		currentPlayer.getHand().remove(card);
		playedCard = true;
		discardPile.add(card);
		if (card instanceof HitCard) {
			for (int i = 0; i < ((HitCard) card).getHitNumber(); i++) {
				getNextPlayer().getHand().add(deck.remove(deck.size() - 1));
				if (deck.size() == 0) {
					rebuildDeck();
				}
			}
		}
	}

	public void playCard(Card card, Color color) throws UnallowedCardException {
		Player currentPlayer = players.get(0);
		if (!currentPlayer.getHand().contains(card))
			throw new UnallowedCardException("You can only play a card from your hand.");
		currentPlayer.getHand().remove(card);
		playedCard = true;
		discardPile.add(card);
		((Changeable) card).changeColor(color);
		if (card instanceof HitAndChangeCard) {
			for (int i = 0; i < ((HitAndChangeCard) card).getHitNumber(); i++) {
				getNextPlayer().getHand().add(deck.remove(deck.size() - 1));
				if (deck.size() == 0) {
					rebuildDeck();
				}
			}
		}
	}

	public void draw() throws MaxCardsDrawnException {
		if (currentDrawn >= 2)
			throw new MaxCardsDrawnException("You've already drawn two cards. Either play or end turn");
		else {
			Player currentPlayer = players.get(0);
			currentPlayer.getHand().add(deck.remove(deck.size() - 1));
			currentDrawn++;
			if (deck.size() == 0) {
				rebuildDeck();
			}
		}
	}

	private void rebuildDeck() {
		Card latest = getLatestDiscard();
		discardPile.remove(latest);
		Collections.shuffle(discardPile);
		deck.addAll(discardPile);
		discardPile.clear();
		discardPile.add(latest);
	}

	public void endTurn() {
		Player current = players.get(0);
		players.remove(current);
		players.add(current);
		currentDrawn = 0;
		if (playedCard) {
			Card latest = getLatestDiscard();
			if (latest instanceof ActionCard || latest instanceof HitAndChangeCard) {
				players.add(players.remove(0));
			}
		}
		playedCard = false;
	}

}
