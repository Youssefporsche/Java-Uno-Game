package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class M2PublicTests {

	String colorPath = "model.cards.Color";
	String cardPath = "model.cards.Card";
	String colorCardPath = "model.cards.ColorCard";
	String wildCardPath = "model.cards.WildCard";
	String numberCardPath = "model.cards.NumberCard";
	String actionCardPath = "model.cards.ActionCard";
	String hitCardPath = "model.cards.HitCard";
	String skipCardPath = "model.cards.SkipCard";
	String changeCardPath = "model.cards.ChangeCard";
	String hitAndChangeCardPath = "model.cards.HitAndChangeCard";
	String changeablePath = "model.cards.Changeable";

	String gamePath = "engine.Game";
	String playerPath = "engine.Player";

	String gameActionExceptionPath = "exceptions.GameActionException";
	String unallowedCardExceptionPath = "exceptions.UnallowedCardException";
	String maxCardsDrawnExceptionPath = "exceptions.MaxCardsDrawnException";

	@Test(timeout = 3000)
	public void testChangeableIsInterface() {
		try {
			testIsInterface(Class.forName(changeablePath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an interface called Changeable.", false);
		}
	}

	@Test(timeout = 3000)
	public void testChangeColorExistsChangeable() {
		Method m = null;
		try {
			m = Class.forName(changeablePath).getDeclaredMethod("changeColor", Class.forName(colorPath));
		} catch (NoSuchMethodException e) {
			assertTrue(
					"Interface \"Changeable\" should contain an abstract method called \"changeColor\", with the correct parameters.",
					false);
		} catch (SecurityException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an interface called \"Changeable\".", false);
		}
		assertFalse("Method \"changeColor\" in interface \"Changeable\" should not be static.",
				Modifier.isStatic(m.getModifiers()));
		assertTrue("Method \"changeColor\" in interface \"Changeable\" should not return anything.",
				m.getReturnType().equals(Void.TYPE));

	}

	@Test(timeout = 3000)
	public void testWildCardImplementsChangeable() {
		try {
			testClassImplementsInterface(Class.forName(wildCardPath), Class.forName(changeablePath));
		} catch (ClassNotFoundException e) {
			assertTrue(e.getClass().getName() + " occurred: " + e.getMessage(), false);
		}
	}

	@Test(timeout = 3000)
	public void testClassIsAbstractCard() {
		try {
			testClassIsAbstract(Class.forName(cardPath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an abstract class called Card.", false);
		}
	}

	@Test(timeout = 3000)
	public void testClassIsAbstractWildCard() {
		try {
			testClassIsAbstract(Class.forName(wildCardPath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an abstract class called WildCard.", false);
		}
	}

	@Test(timeout = 3000)
	public void testClassIsAbstractColorCard() {
		try {
			testClassIsAbstract(Class.forName(colorCardPath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an abstract class called ColorCard.", false);
		}
	}

	@Test(timeout = 3000)
	public void testClassIsAbstractActionCard() {
		try {
			testClassIsAbstract(Class.forName(actionCardPath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package model.cards should contain an abstract class called ActionCard.", false);
		}
	}

	@Test(timeout = 3000)
	public void testClassIsAbstractGameActionException() {
		try {
			testClassIsAbstract(Class.forName(gameActionExceptionPath));
		} catch (ClassNotFoundException e) {
			assertTrue("Package exceptions should contain an abstract class called GameActionException.", false);
		}
	}

	@Test(timeout = 3000)
	public void testChangeColorBehavior() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> constructor = Class.forName(hitCardPath).getConstructor(Class.forName(colorPath));

		int r2 = (int) (Math.random() * 4);
		Object color = null;
		if (r2 == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r2 == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r2 == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r2 == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");

		Constructor<?> constructor2 = Class.forName(changeCardPath).getConstructor();
		Object b = constructor2.newInstance();

		Method m2 = null;
		Class curr = b.getClass();
		while (m2 == null) {

			if (curr == Object.class)
				fail("Class " + b.getClass().getSimpleName() + " should have the method\"changeColor" + "\".");
			try {
				m2 = curr.getDeclaredMethod("changeColor", (Class.forName(colorPath)));
			} catch (NoSuchMethodException e) {
				curr = curr.getSuperclass();
			}

		}
		m2.invoke(b, color);

		String[] varNames = { "color" };
		Object[] varValues = { color };
		testMethodChangeVariable(b, varNames, varValues);

	}

	@Test(timeout = 1000)
	public void testInstanceVariablePlayedCardIsPresentInClassGame() throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "playedCard", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariablePlayedCardIsPrivateInClassGame() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(gamePath), "playedCard");
	}

	@Test(timeout = 100)
	public void testInstanceVariablePlayedCardIsBooleanInGame() throws Exception {
		testInstanceVariableOfType(Class.forName(gamePath), "playedCard", boolean.class);
	}

	@Test(timeout = 3000)
	public void testIsGetNextPlayerMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named getNextPlayer.",
				containsMethodName(methods, "getNextPlayer"));
	}

	@Test(timeout = 3000)
	public void testIsGetLatestDiscardMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named getLatestDiscard.",
				containsMethodName(methods, "getLatestDiscard"));
	}

	@Test(timeout = 3000)
	public void testIsDrawMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named draw.", containsMethodName(methods, "draw"));
	}

	@Test(timeout = 3000)
	public void testIsRebuildDeckMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named rebuildDeck.", containsMethodName(methods, "rebuildDeck"));
	}

	@Test(timeout = 3000)
	public void testIsCheckGameOverMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named checkGameOver.",
				containsMethodName(methods, "checkGameOver"));
	}

	@Test(timeout = 3000)
	public void testIsPlayCardMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named playCard.", containsMethodName(methods, "playCard"));
	}

	@Test(timeout = 3000)
	public void testIsEndTurnMethodExists() {
		Method[] methods = createGame().getClass().getDeclaredMethods();
		assertTrue("Class Game should contain a method named endTurn.", containsMethodName(methods, "endTurn"));
	}

	@Test(timeout = 3000)
	public void testCheckGameOverPlayerOneWins() throws Exception {

		Object createdGame = createGame();
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);

		Method getHand = players.get(0).getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(0));

		hand.clear();

		try {

			Method m = createdGame.getClass().getMethod("checkGameOver");
			Object winningPlayer = m.invoke(createdGame);
			assertEquals("checkGameOver should return the winning player.", players.get(0), winningPlayer);

		} catch (NoSuchMethodException e) {
			fail("Game class should have checkGameOver method");
		}

	}

	@Test(timeout = 3000)
	public void testCheckGameOverPlayerTwoWins() throws Exception {

		Object createdGame = createGame();
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);

		Method getHand = players.get(1).getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(1));

		hand.clear();

		try {

			Method m = createdGame.getClass().getMethod("checkGameOver");
			Object winningPlayer = m.invoke(createdGame);
			assertEquals("checkGameOver should return the winning player.", players.get(1), winningPlayer);

		} catch (NoSuchMethodException e) {
			fail("Game class should have checkGameOver method");
		}

	}

	@Test(timeout = 3000)
	public void testCheckGameOverPlayerThreeWins() throws Exception {

		Object createdGame = createGame();
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);

		Method getHand = players.get(2).getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(2));

		hand.clear();

		try {

			Method m = createdGame.getClass().getMethod("checkGameOver");
			Object winningPlayer = m.invoke(createdGame);
			assertEquals("checkGameOver should return the winning player.", players.get(2), winningPlayer);

		} catch (NoSuchMethodException e) {
			fail("Game class should have checkGameOver method");
		}

	}

	@Test(timeout = 3000)
	public void testCheckGameOverPlayerFourWins() throws Exception {

		Object createdGame = createGame();
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);

		Method getHand = players.get(3).getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(3));

		hand.clear();

		try {

			Method m = createdGame.getClass().getMethod("checkGameOver");
			Object winningPlayer = m.invoke(createdGame);
			assertEquals("checkGameOver should return the winning player.", players.get(3), winningPlayer);

		} catch (NoSuchMethodException e) {
			fail("Game class should have checkGameOver method");
		}

	}

	@Test(timeout = 3000)
	public void testCheckGameOverNoWinner() throws Exception {

		Object createdGame = createGame();

		try {

			Method m = createdGame.getClass().getMethod("checkGameOver");
			Object winningPlayer = m.invoke(createdGame);
			assertEquals("checkGameOver should return the winning player", winningPlayer, null);

		} catch (NoSuchMethodException e) {
			fail("Game class should have checkGameOver method");
		}

	}

	@Test(timeout = 3000)
	public void testgetLatestDiscardBase() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		Object latest = pile.get(pile.size() - 1);

		try {

			Method m = createdGame.getClass().getMethod("getLatestDiscard");
			Object l = m.invoke(createdGame);
			assertEquals("getLatestDiscard should return the last added card to the discard pile", l, latest);

		} catch (NoSuchMethodException e) {
			fail("Game class should have getLatestDiscard method");
		}

	}

	@Test(timeout = 3000)
	public void testgetLatestDiscard() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		int r = (int) (Math.random() * 100);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 5);
			if (r2 == 0) {
				pile.add(createChangeCard());
			} else if (r2 == 1) {
				pile.add(createHitAndChangeCard());
			} else if (r2 == 2) {
				pile.add(createSkipCard((int) (Math.random() * 4)));
			} else if (r2 == 3) {
				pile.add(createHitCard((int) (Math.random() * 4)));
			} else {
				pile.add(createNumberCard((int) (Math.random() * 10), (int) (Math.random() * 4)));
			}
		}
		Object latest = pile.get(pile.size() - 1);

		try {

			Method m = createdGame.getClass().getMethod("getLatestDiscard");
			Object l = m.invoke(createdGame);
			assertEquals("getLatestDiscard should return the last added card to the discard pile", l, latest);

		} catch (NoSuchMethodException e) {
			fail("Game class should have getLatestDiscard method");
		}

	}

	@Test(timeout = 3000)
	public void testgetNextPlayer() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object next = pile.get(1);

		try {

			Method m = createdGame.getClass().getMethod("getNextPlayer");
			Object l = m.invoke(createdGame);
			assertEquals("getNextPlayer should return the player whose turn takes place after the current player", l,
					next);

		} catch (NoSuchMethodException e) {
			fail("Game class should have getNextPlayer method");
		}

	}

	@Test(timeout = 3000)
	public void testgetNextPlayer2() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		pile.remove((int) (Math.random() * 4));
		Object next = pile.get(1);

		try {

			Method m = createdGame.getClass().getMethod("getNextPlayer");
			Object l = m.invoke(createdGame);
			assertEquals("getNextPlayer should return the player whose turn takes place after the current player", l,
					next);

		} catch (NoSuchMethodException e) {
			fail("Game class should have getNextPlayer method");
		}

	}

	@Test(timeout = 3000)
	public void testgetNextPlayer3() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		pile.remove((int) (Math.random() * 4));
		pile.remove((int) (Math.random() * 3));
		Object next = pile.get(1);

		try {

			Method m = createdGame.getClass().getMethod("getNextPlayer");
			Object l = m.invoke(createdGame);
			assertEquals("getNextPlayer should return the player whose turn takes place after the current player", l,
					next);

		} catch (NoSuchMethodException e) {
			fail("Game class should have getNextPlayer method");
		}

	}

	@Test(timeout = 3000)
	public void testRebuildDeck() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		pile.addAll(deck);
		deck.clear();

		try {

			Method m = createdGame.getClass().getMethod("getLatestDiscard");
			Object l = m.invoke(createdGame);
			Method m2 = createdGame.getClass().getDeclaredMethod("rebuildDeck");
			m2.setAccessible(true);
			m2.invoke(createdGame);
			assertEquals("rebuildDeck should empty all but the latest pile in discard", 1, pile.size());
			assertEquals("rebuildDeck should empty all but the latest pile in discard", l, pile.get(pile.size() - 1));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}

	}

	@Test(timeout = 3000)
	public void testRebuildDeck2() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		pile.addAll(deck);
		deck.clear();

		try {

			Method m = createdGame.getClass().getMethod("getLatestDiscard");
			Object l = m.invoke(createdGame);
			Method m2 = createdGame.getClass().getDeclaredMethod("rebuildDeck");
			m2.setAccessible(true);
			m2.invoke(createdGame);
			assertEquals(
					"rebuildDeck should not remove any of the cards from the full set of both the discard and deck combined",
					100 - (28 - r), pile.size() + deck.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testRebuildDeck3() throws Exception {

		Object createdGame = createGame();

		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		pile.addAll(deck);
		deck.clear();

		try {

			Method m = createdGame.getClass().getMethod("getLatestDiscard");
			Object l = m.invoke(createdGame);
			Method m2 = createdGame.getClass().getDeclaredMethod("rebuildDeck");
			m2.setAccessible(true);
			m2.invoke(createdGame);
			assertEquals("rebuildDeck should not add the remainder of the cards that were in discard to the deck",
					72 + r - 1, deck.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn() throws Exception {

		Object createdGame = createGame();

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c = players.get(1);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should go to the next player", players.get(0), c);
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn2() throws Exception {

		Object createdGame = createGame();

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c = players.get(0);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should place player ending their turn in the correct position for the next round",
					players.get(players.size() - 1), c);
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn3() throws Exception {

		Object createdGame = createGame();

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		int c = players.size();

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should not remove any player from the game", players.size(), c);
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn4() throws Exception {

		Object createdGame = createGame();

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 2);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should reset the number of drawn cards.", 0, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn5() throws Exception {

		Object createdGame = createGame();

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		f.set(createdGame, true);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should reset that the player has not played a card.", false, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn6() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createHitAndChangeCard();
		pile.add(c);

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		f.set(createdGame, true);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(2);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should skip the next player if a hit and change card was played.", c1,
					players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn7() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createHitCard(2);
		pile.add(c);

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		f.set(createdGame, true);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(2);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should skip the next player if a hit card was played.", c1, players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn8() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createSkipCard(2);
		pile.add(c);

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		f.set(createdGame, true);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(2);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should skip the next player if a skip card was played.", c1, players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn9() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createHitAndChangeCard();
		pile.add(c);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals(
					"EndTurn should not skip the next player if a hit and change card was played by the player before last.",
					c1, players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn10() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createHitCard(2);
		pile.add(c);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should not skip the next player if a hit card was played by the player before last.",
					c1, players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testEndTurn11() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Object c = createSkipCard(2);
		pile.add(c);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);

		try {

			Method m = createdGame.getClass().getMethod("endTurn");
			Object l = m.invoke(createdGame);
			assertEquals("EndTurn should not skip the next player if a skip card was played by the player before last.",
					c1, players.get(0));
		} catch (NoSuchMethodException e) {
			fail("Game class should have rebuildDeckMethod method");
		}
	}

	@Test(timeout = 3000)
	public void testDraw() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 2);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		try {
			Method m = createdGame.getClass().getDeclaredMethod("draw");
			m.invoke(createdGame);
			fail("Trying to draw a third card should throw an exception.");
		} catch (NoSuchMethodException e) {
			fail("Game class should have draw method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(maxCardsDrawnExceptionPath).equals(e.getCause().getClass())))
					fail("Trying to draw a third card should throw an exception.");
			} catch (ClassNotFoundException e1) {
				fail("There should be an MaxCardsDrawnException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testDraw2() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 0);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		try {
			Method m = createdGame.getClass().getDeclaredMethod("draw");
			m.invoke(createdGame);
			assertEquals("After drawing a card the number of drawn Cards should increase.", 1, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have draw method");
		}
	}

	@Test(timeout = 3000)
	public void testDraw3() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 1);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		try {
			Method m = createdGame.getClass().getDeclaredMethod("draw");
			m.invoke(createdGame);
			assertEquals("After drawing a card the number of drawn Cards should increase.", 2, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have draw method");
		}
	}

	@Test(timeout = 3000)
	public void testDraw4() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		Object y = deck.get(deck.size() - 1);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		int x = ((ArrayList<Object>) (c1.getClass().getDeclaredMethod("getHand").invoke(c1))).size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("draw");
			m.invoke(createdGame);
			assertEquals("After drawing a card, the player should have it placed in his hand", x + 1,
					((ArrayList<Object>) (c1.getClass().getDeclaredMethod("getHand").invoke(c1))).size());
			assertEquals("The player should draw the correct card from the deck.", y,
					((ArrayList<Object>) (c1.getClass().getDeclaredMethod("getHand").invoke(c1))).get(
							((ArrayList<Object>) (c1.getClass().getDeclaredMethod("getHand").invoke(c1))).size() - 1));
		} catch (NoSuchMethodException e) {
			fail("Game class should have draw method");
		}
	}

	@Test(timeout = 3000)
	public void testDraw5() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		Object y = deck.get(deck.size() - 1);
		pile.addAll(deck);
		deck.clear();
		deck.add(y);
		pile.remove(y);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);

		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}

		Object c1 = players.get(0);
		int x = ((ArrayList<Object>) (c1.getClass().getDeclaredMethod("getHand").invoke(c1))).size();

		int z = pile.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("draw");
			m.invoke(createdGame);
			assertEquals("After drawing a card, if the deck is empty it should be rebuilt", z - 1, deck.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have draw method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 2);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		int y = (int) (Math.random() * 4);
		Object color = null;
		if (y == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (y == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (y == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (y == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		Object c = createChangeCard();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath),
					Class.forName(colorPath));
			m.invoke(createdGame, c, color);
			fail("Attempting to play a card not in your hand should throw an exception");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("Attempting to play a card not in your hand should throw an exception");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal() throws Exception {

		Object createdGame = createGame();
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("currentDrawn");
		f.setAccessible(true);
		f.set(createdGame, 2);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(1);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		int y = (int) (Math.random() * 4);
		Object color = null;
		if (y == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (y == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (y == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (y == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");

		int r3 = (int) (Math.random() * 3);
		Object c = null;
		if (r3 == 0) {
			c = createNumberCard((int) (Math.random() * 10), y);
		} else if (r3 == 1) {
			c = createHitCard(y);
		} else if (r3 == 2) {
			c = createSkipCard(y);
		}

		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, c);
			fail("Attempting to play a card not in your hand should throw an exception");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("Attempting to play a card not in your hand should throw an exception");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal2() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int x = 0, y = 0;
		while (x == y) {
			x = (int) (Math.random() * 4);
			y = (int) (Math.random() * 4);
		}

		Object ca1 = createNumberCard((int) (Math.random() * 10), x);
		Object ca2 = createSkipCard(y);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal3() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int x = 0, y = 0;
		while (x == y) {
			x = (int) (Math.random() * 4);
			y = (int) (Math.random() * 4);
		}

		Object ca1 = createNumberCard((int) (Math.random() * 10), x);
		Object ca2 = createHitCard(y);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal4() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int x = 0, y = 0;
		while (x == y) {
			x = (int) (Math.random() * 4);
			y = (int) (Math.random() * 4);
		}

		Object ca1 = createNumberCard((int) (Math.random() * 10), x);
		Object ca2 = createSkipCard(y);
		pile.add(ca1);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca2);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca2);
			fail("If the player is attempting to play a skip card incorrectly, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("If the player is attempting to play a skip card incorrectly, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal5() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int x = 0, y = 0;
		while (x == y) {
			x = (int) (Math.random() * 4);
			y = (int) (Math.random() * 4);
		}

		Object ca1 = createNumberCard((int) (Math.random() * 10), x);
		Object ca2 = createHitCard(y);
		pile.add(ca1);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca2);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca2);
			fail("If the player is attempting to play a hit card incorrectly, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("If the player is attempting to play a hit card incorrectly, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal6() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int x = 0, y = 0;
		while (x == y) {
			x = (int) (Math.random() * 10);
			y = (int) (Math.random() * 10);
		}
		int r = 0, r2 = 0;
		while (r == r2) {
			r = (int) (Math.random() * 4);
			r2 = (int) (Math.random() * 4);
		}

		Object ca1 = createNumberCard(x, r);
		Object ca2 = createNumberCard(y, r2);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			try {
				if (!(Class.forName(unallowedCardExceptionPath).equals(e.getCause().getClass())))
					fail("If the player is attempting to play a number card incorrectly, an exception should be thrown");
			} catch (ClassNotFoundException e1) {
				fail("There should be an UnAllowedCardException class");
			}

		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal7() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int r = 0;
		r = (int) (Math.random() * 4);

		Object ca1 = createNumberCard((int) (Math.random() * 10), r);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("Played card should be placed in discard pile.", ca1, pile.get(pile.size() - 1));
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal8() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createNumberCard((int) (Math.random() * 10), r);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> supposedhand = new ArrayList<>();
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		supposedhand.addAll(hand);
		hand.add(ca1);
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("Played card should be removed from players hand.", x - 1, hand.size());
			assertEquals("Played card should be removed from players hand.", hand, supposedhand);
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal9() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createNumberCard((int) (Math.random() * 10), r);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("Played card should be removed from players hand.", x - 1, hand.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal10() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createNumberCard((int) (Math.random() * 10), r);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("PlayCard method should update that the player has played a card", true, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal11() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		Object c2 = players.get(1);

		int r = 10;
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createHitCard(r);
		Field f2 = ca1.getClass().getDeclaredField("hitNumber");
		f2.setAccessible(true);
		int h = (int) f2.get(ca1);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		Object ca3 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		ArrayList<Object> hand2 = (ArrayList<Object>) getHand.invoke(c2);
		int s = hand2.size();
		int ss = s + h;
		hand.add(ca1);
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("PlayCard method should add cards to next player if hit card was played.", ss, hand2.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardNormal12() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		Object c2 = players.get(1);

		int r = 10;
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createHitCard(r);
		Field f2 = ca1.getClass().getDeclaredField("hitNumber");
		f2.setAccessible(true);
		int h = (int) f2.get(ca1);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		Object ca3 = createNumberCard((int) (Math.random() * 10), r);
		deck.clear();
		deck.add(ca3);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		ArrayList<Object> hand2 = (ArrayList<Object>) getHand.invoke(c2);
		int s = hand2.size();
		int ss = s + h;
		hand.add(ca1);
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath));
			m.invoke(createdGame, ca1);
			assertEquals("PlayCard method should rebuild the deck if needed.", 13 - h, deck.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			fail("PlayCard method should rebuild the deck if needed.");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild1() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);

		int r = 0;
		r = (int) (Math.random() * 4);

		Object ca1 = createChangeCard();
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath),
					Class.forName(colorPath));
			m.invoke(createdGame, ca1, color);
			assertEquals("Played card should be placed in discard pile.", ca1, pile.get(pile.size() - 1));
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild2() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createChangeCard();
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> supposedhand = new ArrayList<>();
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		supposedhand.addAll(hand);
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("Played card should be removed from players hand.", x - 1, hand.size());
			assertEquals("Played card should be removed from players hand.", hand, supposedhand);
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild3() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createChangeCard();
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("Played card should be removed from players hand.", x - 1, hand.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild4() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);

		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		int r = (int) (Math.random() * 28);
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createChangeCard();
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("PlayCard method should update that the player has played a card", true, f.get(createdGame));
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild6() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		Object c2 = players.get(1);

		int r = 10;
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createHitAndChangeCard();
		Field f2 = ca1.getClass().getDeclaredField("hitNumber");
		f2.setAccessible(true);
		int h = (int) f2.get(ca1);
		Object ca3 = createNumberCard((int) (Math.random() * 10), r);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		ArrayList<Object> hand2 = (ArrayList<Object>) getHand.invoke(c2);
		int s = hand2.size();
		int ss = s + h;
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("PlayCard method should add cards to next player if hit card was played.", ss, hand2.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild7() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		Object c2 = players.get(1);

		int r = 10;
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createHitAndChangeCard();
		Field f2 = ca1.getClass().getDeclaredField("hitNumber");
		f2.setAccessible(true);
		int h = (int) f2.get(ca1);
		Object ca2 = createNumberCard((int) (Math.random() * 10), r);
		Object ca3 = createNumberCard((int) (Math.random() * 10), r);
		deck.clear();
		deck.add(ca3);
		pile.add(ca2);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		ArrayList<Object> hand2 = (ArrayList<Object>) getHand.invoke(c2);
		int s = hand2.size();
		int ss = s + h;
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("PlayCard method should rebuild the deck if needed.", 13 - h, deck.size());
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		} catch (InvocationTargetException e) {
			fail("PlayCard method should rebuild the deck if needed.");
		}
	}

	@Test(timeout = 3000)
	public void testPlayCardWild8() throws Exception {

		Object createdGame = createGame();
		ArrayList pile = (ArrayList) createdGame.getClass().getMethod("getDiscardPile").invoke(createdGame);
		ArrayList deck = (ArrayList) createdGame.getClass().getMethod("getDeck").invoke(createdGame);
		Field f = createdGame.getClass().getDeclaredField("playedCard");
		f.setAccessible(true);
		ArrayList players = (ArrayList) createdGame.getClass().getMethod("getPlayers").invoke(createdGame);
		Object c1 = players.get(0);
		Object c2 = players.get(1);

		int r = 10;
		for (int i = 0; i < r; i++) {
			int r2 = (int) (Math.random() * 4);
			Method getHand = players.get(r2).getClass().getMethod("getHand");
			ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(players.get(r2));
			if (hand.size() > 0)
				pile.add(hand.remove((int) (Math.random() * hand.size())));
			else
				i--;
		}
		r = (int) (Math.random() * 4);

		Object ca1 = createHitAndChangeCard();
		Field f2 = ca1.getClass().getDeclaredField("hitNumber");
		f2.setAccessible(true);
		int h = (int) f2.get(ca1);
		Object ca3 = createNumberCard((int) (Math.random() * 10), r);
		Method getHand = c1.getClass().getMethod("getHand");
		ArrayList<Object> hand = (ArrayList<Object>) getHand.invoke(c1);
		ArrayList<Object> hand2 = (ArrayList<Object>) getHand.invoke(c2);
		int s = hand2.size();
		int ss = s + h;
		hand.add(ca1);
		Object color = null;
		r = (int) (Math.random() * 4);
		if (r == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (r == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (r == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (r == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");
		int x = hand.size();
		Field f3 = ca1.getClass().getSuperclass().getSuperclass().getDeclaredField("color");
		f3.setAccessible(true);
		try {
			Method m = createdGame.getClass().getDeclaredMethod("playCard", Class.forName(cardPath), color.getClass());
			m.invoke(createdGame, ca1, color);
			assertEquals("PlayCard method should change Wild Card color to requested color.", color, f3.get(ca1));
		} catch (NoSuchMethodException e) {
			fail("Game class should have playCard method");
		}
	}

	private Object createHitAndChangeCard()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {

		Constructor<?> constructor2 = Class.forName(hitAndChangeCardPath).getConstructor();
		Object b = constructor2.newInstance();
		return b;

	}

	private Object createChangeCard() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {

		Constructor<?> constructor2 = Class.forName(changeCardPath).getConstructor();
		Object b = constructor2.newInstance();
		return b;

	}

	private Object createHitCard(int y) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		Object color = null;
		if (y == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (y == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (y == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (y == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");

		Constructor<?> constructor2 = Class.forName(hitCardPath).getConstructor(Class.forName(colorPath));
		Object b = constructor2.newInstance(color);
		return b;

	}

	private Object createSkipCard(int y)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		Object color = null;
		if (y == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (y == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (y == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (y == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");

		Constructor<?> constructor2 = Class.forName(skipCardPath).getConstructor(Class.forName(colorPath));
		Object b = constructor2.newInstance(color);
		return b;

	}

	private Object createNumberCard(int x, int y)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		Object color = null;
		if (y == 0)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "RED");
		else if (y == 1)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "GREEN");
		else if (y == 2)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "BLUE");
		else if (y == 3)
			color = Enum.valueOf((Class<Enum>) Class.forName(colorPath), "YELLOW");

		Constructor<?> constructor2 = Class.forName(numberCardPath).getConstructor(int.class, Class.forName(colorPath));
		Object b = constructor2.newInstance(x, color);
		return b;

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private Object createPlayer(String s) {
		try {
			Constructor<?> constructorFirstPlayer = Class.forName(playerPath).getConstructor(String.class);
			Object firstPlayer = constructorFirstPlayer.newInstance(s);
			return firstPlayer;

		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {

			fail(e.getCause().getClass() + " occurred");
			return null;
		} catch (InstantiationException e) {

			fail(e.getCause().getClass() + " occurred");
			return null;
		} catch (IllegalAccessException e) {

			fail(e.getCause().getClass() + " occurred");
			return null;
		} catch (IllegalArgumentException e) {

			fail(e.getCause().getClass() + " occurred");
			return null;
		} catch (InvocationTargetException e) {

			fail(e.getCause().getClass() + " occurred");
			return null;
		}

	}

	private Object createGame() {
		Constructor<?> gameConstructor;
		try {
			gameConstructor = Class.forName(gamePath).getConstructor(ArrayList.class);
			ArrayList<Object> x = new ArrayList<Object>();
			for (int i = 0; i < 4; i++) {
				x.add(createPlayer("" + i));
			}
			Object createdGame = gameConstructor.newInstance(x);
			return createdGame;
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {

			fail(e.getCause().getClass() + " occurred");
		} catch (InstantiationException e) {

			fail(e.getCause().getClass() + " occurred");
		} catch (IllegalAccessException e) {

			fail(e.getCause().getClass() + " occurred");
		} catch (IllegalArgumentException e) {

			fail(e.getCause().getClass() + " occurred");
		} catch (InvocationTargetException e) {

			fail(e.getCause().getClass() + " occurred");
		}

		return null;
	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {
		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		boolean b = true;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			b = false;
		}
		if (b) {
			Class varType = f.getType();
			assertEquals("The attribute " + varType.getSimpleName() + " of instance variable: " + varName
					+ " should be of the type " + expectedType.getSimpleName(), expectedType, varType);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should be declared in class " + aClass.getSimpleName()
					+ ".", false);
		}

	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		boolean b = 2 == f.getModifiers();
		assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
				+ " should not be accessed outside that class.", b);
	}

	private void testMethodChangeVariable(Object createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}

			}

			f.setAccessible(true);
			assertEquals(
					"The method called from the " + createdObject.getClass().getSimpleName()
							+ " class should adjust the variable \"" + currName + "\" correctly.",
					currValue, f.get(createdObject));
		}

	}

	private void testClassImplementsInterface(Class<?> aClass, Class<?> iClass) {
		assertTrue("Class \"" + aClass.getSimpleName() + "\" should implement \"" + iClass.getSimpleName()
				+ "\" interface.", iClass.isAssignableFrom(aClass));
	}

	private void testClassIsAbstract(Class<?> aClass) {
		assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	private void testIsInterface(Class<?> aClass) {
		assertTrue("\"" + aClass.getSimpleName() + "\" is an interface.", Modifier.isInterface(aClass.getModifiers()));
	}

	public String deckHelper3(Object o) throws NoSuchFieldException, SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {
		String curr = o.getClass().getSimpleName();
		Field f = Class.forName(cardPath).getDeclaredField("color");
		f.setAccessible(true);
		curr = curr + f.get(o).toString();
		if (o.getClass().equals(Class.forName(numberCardPath))) {
			Field f2 = Class.forName(numberCardPath).getDeclaredField("number");
			f2.setAccessible(true);
			curr = curr + f2.get(o).toString();
		}
		return curr;
	}
}
