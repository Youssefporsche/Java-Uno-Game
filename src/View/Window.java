package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.cards.Card;
import model.cards.NumberCard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;
import exceptions.MaxCardsDrawnException;


public class Window   implements ActionListener {
	ArrayList<Player> players = new ArrayList<Player>();
	Game g = new Game(players);
	
	JFrame Class;
	
	
	
	Player p1;
	Player p2;
	Player p3;
	Player p4;
	JFrame window;
	JFrame submition;
	
	
	JPanel left;
	JPanel right;
	JPanel up;
	JPanel down;
	JPanel center;

	
	JTextField Submition1;
	JTextField Submition2;
	JTextField Submition3;
	JTextField Submition4; 
	

	
	
	
	
	
	JTextField Submition_left;
	JTextField Submition_right;
	JTextField Submition_up;
	JTextField Submition_down;
	
	
	
	JTextField numberField;
	
	JTextField number_left;
	JTextField number_right;
	JTextField number_up;
	JTextField number_down;
	
	JButton add1;
	JButton add2;
	JButton add3;
	JButton add4;
	JButton play;
	JButton endTurn;
	JButton playButton;
	JButton r1;
	JButton r2;
	JButton r3;
	JButton r4;
	JButton r5;
	JButton r6;
	JButton r7;
	JButton r8;
	JButton r9;
	JButton r0;
	JButton b0;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	JButton b7;
	JButton b8;
	JButton b9;
	
	JButton g0;
	JButton g1;
	JButton g2;
	JButton g3;
	JButton g4;
	JButton g5;
	JButton g6;
	JButton g7;
	JButton g8;
	JButton g9;
	
	
	JButton y0;
	JButton y1;
	JButton y2;
	JButton y3;
	JButton y4;
	JButton y5;
	JButton y6;
	JButton y7;
	JButton y8;
	JButton y9;
	
	
	JButton draw;
	
	JPanel centerlabel;
	
	JLabel left1;
	String[] names =new String [4] ;
	
	int i=0;
	Game game = new Game(players);
NumberCard numberCard =new NumberCard(i, null);
	Window(){
		ArrayList<Player> players = new ArrayList<Player>();
		Game game = new Game(players);			
//		---------------------------------------------------------------------------------------------------------------------
		
		
		window = new JFrame();
		window.setSize(600,600);
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setTitle("UNO_Game");
		window.setLayout(new BorderLayout(10,10));
		//window.setResizable(false);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 left  =new JPanel();
		left.setBackground(Color.blue);
		left.setPreferredSize(new Dimension(100,100) );
		
		 right = new JPanel();
		right.setBackground(Color.red);
		right.setPreferredSize(new Dimension(100,100));
		
		  up   =  new JPanel();
		up.setBackground(Color.green);
		up.setPreferredSize(new Dimension(100,100));
		
		 down  = new JPanel();
		down.setBackground(Color.yellow);
		down.setPreferredSize(new Dimension(100,100));
		
		center = new JPanel();
		center.setLayout(null);
		center.setPreferredSize(new Dimension(200,200));
		center.setBackground(Color.black);
		playButton = new JButton("Play card");
		playButton.addActionListener(this);
		playButton.setBounds(0, 0, 100, 100);
		
		draw =new JButton("Draw Card");
		draw.setBounds(370, 350, 100, 100);
		draw.addActionListener(this);
		
		numberField= new JTextField();
		numberField.setBounds(100, 110, 50, 50);
		
		
		r0=new JButton("r0");
		r0.addActionListener(this);
		
		r1=new JButton("r1");
		
		r1.addActionListener(this);
		
		r2=new JButton("r2");
		r2.addActionListener(this);
		
		r3=new JButton("r3");
		r3.addActionListener(this);
		
		r4=new JButton("r4");
		r4.addActionListener(this);
		
		r5=new JButton("r5");
		r5.addActionListener(this);
		
		r6=new JButton("r6");
		r6.addActionListener(this);
		
		r7=new JButton("r7");
		r7.addActionListener(this);
		
		r8=new JButton("r8");
		r8.addActionListener(this);
		
		r9=new JButton("r9");
		r9.addActionListener(this);

		
		b0=new JButton("b0");
		
		b0.addActionListener(this);
		b1=new JButton("b1");
		
		b1.addActionListener(this);
		b2=new JButton("b2");
		
		b2.addActionListener(this);
		b3=new JButton("b3");
		
		b3.addActionListener(this);
		b4=new JButton("b4");
		
		b4.addActionListener(this);
		b5=new JButton("b5");
		
		b5.addActionListener(this);
		b6=new JButton("b6");
		
		b6.addActionListener(this);
		b7=new JButton("b7");
		
		b7.addActionListener(this);
		b8=new JButton("b8");
		
		b8.addActionListener(this);
		b9=new JButton("b9");
		
		b9.addActionListener(this);
		
		

		g0=new JButton("g0");
		
		g0.addActionListener(this);
		g1=new JButton("g1");
		
		g1.addActionListener(this);
		g2=new JButton("g2");		
		g2.addActionListener(this);
		g3=new JButton("g3");
		
		g3.addActionListener(this);
		g4=new JButton("g4");
		
		g4.addActionListener(this);
		g5=new JButton("g5");
		
		g5.addActionListener(this);
		g6=new JButton("g6");
		
		g6.addActionListener(this);
		g7=new JButton("g7");
		
		g7.addActionListener(this);
		g8=new JButton("g8");
		
		g8.addActionListener(this);
		g9=new JButton("g9");
		
		g9.addActionListener(this);
		
		

		y0=new JButton("y0");
		
		y0.addActionListener(this);
		y1=new JButton("y1");
		
		y1.addActionListener(this);
		y2=new JButton("y2");
		
		y2.addActionListener(this);
		y3=new JButton("y3");
		
		y3.addActionListener(this);
		y4=new JButton("y4");
		
		y4.addActionListener(this);
		y5=new JButton("y5");
		
		y5.addActionListener(this);
		y6=new JButton("y6");
		
		y6.addActionListener(this);
		y7=new JButton("y7");
		
		y7.addActionListener(this);
		y8=new JButton("y8");
		
		y8.addActionListener(this);
		y9=new JButton("y9");
		
		y9.addActionListener(this);
		
		Submition_left= new  JTextField();
		Submition_left.setPreferredSize(new Dimension(100,100));
		Submition_left.setFont(new Font("arial",Font.BOLD,13));
		Submition_left.setBackground(Color.blue);
		Submition_left.setCaretColor(Color.black);
	
		endTurn = new JButton("End your turn");
		//endTurn.setPreferredSize(new Dimension(70,70));
		endTurn.addActionListener(this);
		endTurn.setBounds(0, 250, 150, 100);
		
	number_left	=new JTextField();
	number_left.setSize(50,50);
	
	
	
	number_right =new JTextField();
	number_right.setSize(50,50);
	
	number_up=new JTextField();
	number_up.setSize(50,50);
	
	number_down	=new JTextField();
	number_down.setSize(50,50);
	
		Submition_right= new  JTextField();
		Submition_right.setPreferredSize(new Dimension(100,100));
		Submition_right.setFont(new Font("arial",Font.BOLD,13));
		Submition_right.setBackground(Color.red);
		Submition_right.setCaretColor(Color.black);
		
  		Submition_up= new  JTextField();
		Submition_up.setPreferredSize(new Dimension(100,100));
		Submition_up.setFont(new Font("arial",Font.BOLD,13));
		Submition_up.setBackground(Color.green);
		Submition_up.setCaretColor(Color.black);
		
		Submition_down= new  JTextField();
		Submition_down.setPreferredSize(new Dimension(100,100));
		Submition_down.setFont(new Font("arial",Font.BOLD,13));
		Submition_down.setBackground(Color.yellow);
		Submition_down.setCaretColor(Color.black);
		
		//--------------------------------------------------------------------------------------------------------------------------------------------------\\
		submition=new JFrame();
		submition.setTitle("Registering Players to play");
		submition.setLayout(new GridLayout(4,1,10,10));
		submition.setPreferredSize(new Dimension(800,500));
		submition.setResizable(false);
		 Submition1= new JTextField("Player1");
		Submition1.setPreferredSize(new Dimension(300,150));
		Submition1.setFont(new Font("arial",Font.BOLD,13));
		
		 Submition2= new JTextField("Player 2");
		Submition2.setPreferredSize(new Dimension(300,150));
		
		 Submition3= new JTextField("Player3");
		Submition3.setPreferredSize(new Dimension(300,150));
		
		 Submition4= new JTextField("Player 4");
		Submition4.setPreferredSize(new Dimension(300,150));
		
	centerlabel = new JPanel();
	centerlabel.setBounds(100, 100, 100,150);
	
		
		left.add(Submition_left);
		right.add(Submition_right);
		up.add(Submition_up);
		down.add(Submition_down);
		window.setVisible(false);
		window.add(left,BorderLayout.WEST);
		window.add(right,BorderLayout.EAST);
		window.add(up,BorderLayout.NORTH);
		window.add(down,BorderLayout.SOUTH);
		window.add(draw);
		center.add(playButton);
		window.add(center,BorderLayout.CENTER);
		center.add(endTurn);
		center.add(numberField);
		center.add(centerlabel);
	   left.add(number_left);
	   right.add(number_right);
	   up.add(number_up);
	   down.add(number_down);
	   
		JPanel x=new JPanel();
		x.setPreferredSize(new Dimension(100,100));
		
		JPanel y=new JPanel();
		y.setPreferredSize(new Dimension(100,100));
		
		JPanel z=new JPanel();
		z.setPreferredSize(new Dimension(100,100));
		
		JPanel c=new JPanel();
		c.setPreferredSize(new Dimension(100,100));
		
		
		play=new JButton("play");
		play.setPreferredSize(new Dimension(800,200));
	
		play.addActionListener(this);
		
		
	
		
		add1=new JButton("set");
		add1.addActionListener(this);
		add1.setFocusable(true);
		add2=new JButton("set");
		
		add2.setFocusable(false);
		add2.addActionListener(this);
		add3=new JButton("set");
       add3.setFocusable(false);
		add3.setEnabled(false);
		add3.addActionListener(this);
		add4=new JButton("set");
		add4.setEnabled(false);
		add4.addActionListener(this);
		
		submition.add(x);
		x.add(Submition1);
		x.add(add1);
		submition.add(c);
		c.add(Submition2);
		c.add(add2);
		submition.add(z);
		z.add(Submition3);
		z.add(add3);
		submition.add(y);
		y.add(Submition4);
		y.add(add4);
		
		
	
		
			
	
		submition.add(play);
		submition.setVisible(true);
		submition.pack();
		
		 p1 = new Player(Submition1.getText());
		 p2 = new Player(Submition2.getText());
		 p3 = new Player(Submition3.getText());
		 p4 = new Player(Submition4.getText());
		
		 
		
	
	
	Class = new JFrame();
	Class.setSize(200, 200);
	Class.setVisible(false);
	Class.setLayout(new GridLayout(9,4,10,10));
	
	
	
	Class.add(r0);
	Class.add(r1);
	Class.add(r2);
	Class.add(r3);
	Class.add(r4);
	Class.add(r5);
	Class.add(r6);
	Class.add(r7);
	Class.add(r8);
	Class.add(r9);
	
	
	Class.add(b0);
	Class.add(b1);
	Class.add(b2);
	Class.add(b3);
	Class.add(b4);
	Class.add(b5);
	Class.add(b6);
	Class.add(b7);
	Class.add(b8);
	Class.add(b9);
	

	Class.add(g0);
	Class.add(g1);
	Class.add(g2);
	Class.add(g3);
	Class.add(g4);
	Class.add(g5);
	Class.add(g6);
	Class.add(g7);
	Class.add(g8);
	Class.add(g9);
	
	
	Class.add(y0);
	Class.add(y1);
	Class.add(y2);
	Class.add(y3);
	Class.add(y4);
	Class.add(y5);
	Class.add(y6);
	Class.add(y7);
	Class.add(y8);
	Class.add(y9);
	
	Class.pack();
	
	
	players.toString();
		
	//window.pack();
	
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add1) {
			Submition_left.setText(Submition1.getText());
			Submition_left.setEditable(false);
			//p1.getName().equals(Submition1.getText());
			add1.setEnabled(false);
			
			players.add(p1);
			 g.distribute();
			int Hand = p1.getHand().size();
			JLabel l1 = new JLabel("remaining" + Hand + "Cards");
			left.add(l1);
			
		}
		if(e.getSource()==add2){
			Submition_right.setText(Submition2.getText());
			p2.getName().equals(Submition2.getText());
			Submition_right.setEditable(false);
			players.add(p2);
		add2.setEnabled(false);	
		add3.setEnabled(true);
			play.setEnabled(true);
	
		}
		if(e.getSource()==add3){
			p3.getName().equals(Submition3.getText());
			Submition_up.setText(Submition3.getText());
			Submition_up.setEditable(false);
			add3.setEnabled(false);	
			add4.setEnabled(true);
			players.add(p3);
			}
		if(e.getSource()==add4){
			p4.getName().equals(Submition4.getText());
			Submition_down.setText(Submition4.getText());
			Submition_down.setEditable(false);
			add4.setEnabled(false);
			players.add(p4);
			}
		if(e.getSource()==play)  {
			if(!add1.isEnabled()  && !add2.isEnabled()) {
				
				
			submition.dispose();

			window.setVisible(true);
			
			
		}else {
			JOptionPane.showMessageDialog(null, "  2 players are needed to play the game", null, JOptionPane.PLAIN_MESSAGE); 
		}}
		
		if(e.getSource()==playButton) {
			Class.setVisible(true);
			
		}
		if(e.getSource()==r0|| e.getSource()==r1||e.getSource()==r2||e.getSource()==r3||e.getSource()==r4||e.getSource()==r5||e.getSource()==r6||e.getSource()==r7||e.getSource()==r8||e.getSource()==r9) {
			Class.dispose();
			
			centerlabel.setBackground(Color.red);
			playButton.setEnabled(false);
		}
		if(e.getSource()==g0||e.getSource()==g1||e.getSource()==g2||e.getSource()==g3||e.getSource()==g4||e.getSource()==g5||e.getSource()==g6||e.getSource()==g7||e.getSource()==g8||e.getSource()==g9) {
			Class.dispose();
		
			centerlabel.setBackground(Color.green);
			playButton.setEnabled(false);
	
		}
		if(e.getSource()==b0||e.getSource()==b1||e.getSource()==b2||e.getSource()==b3||e.getSource()==b4||e.getSource()==b5||e.getSource()==b6||e.getSource()==b7||e.getSource()==b8||e.getSource()==b9) {
			Class.dispose();
			centerlabel.setBackground(Color.BLUE);
			playButton.setEnabled(false);
		}
		if(e.getSource()==y0||e.getSource()==y1||e.getSource()==y2||e.getSource()==y3||e.getSource()==y4||e.getSource()==y5||e.getSource()==y6||e.getSource()==y7||e.getSource()==y8||e.getSource()==y9) {
			Class.dispose();
			centerlabel.setBackground(Color.yellow);
			playButton.setEnabled(false);
		}
		if(e.getSource()==endTurn )
			
		{if( !playButton.isEnabled()) {
			playButton.setEnabled(true);
			//game.endTurn();
			game.getNextPlayer();
			JOptionPane.showMessageDialog(null, " Who is next to play", null, JOptionPane.PLAIN_MESSAGE);
			
		}else {
			JOptionPane.showMessageDialog(null," you must play or Draw to end turn ", null, JOptionPane.PLAIN_MESSAGE);
			
		}}
		if(e.getSource()==r0||e.getSource()==g0||e.getSource()==y0||e.getSource()==b0) {
			numberField.setText("0");
			
		}
		if(e.getSource()==r1||e.getSource()==g1||e.getSource()==y1||e.getSource()==b1) {
			numberField.setText("1");
		}
		if(e.getSource()==r2||e.getSource()==g2||e.getSource()==y2||e.getSource()==b2) {
			numberField.setText("2");
		}
		if(e.getSource()==r3||e.getSource()==g3||e.getSource()==y3||e.getSource()==b3) {
			numberField.setText("3");
		}
		if(e.getSource()==r4||e.getSource()==g4||e.getSource()==y4||e.getSource()==b4) {
			numberField.setText("4");
		}
		if(e.getSource()==r5||e.getSource()==g5||e.getSource()==y5||e.getSource()==b5) {
			numberField.setText("5");
		}
		if(e.getSource()==r6||e.getSource()==g6||e.getSource()==y6||e.getSource()==b6) {
			numberField.setText("6");
		}
		if(e.getSource()==r7||e.getSource()==g7||e.getSource()==y7||e.getSource()==b7) {
			numberField.setText("7");
		}
		if(e.getSource()==r8||e.getSource()==g8||e.getSource()==y8||e.getSource()==b8) {
			numberField.setText("8");
		}
		if(e.getSource()==r9||e.getSource()==g9||e.getSource()==y9||e.getSource()==b9) {
			numberField.setText("9");
		}
		if(e.getSource()==draw) {
			try {
				game.draw();
				int HandCards = players.get(0).getHand().size() ;
				HandCards ++ ;
			} catch (MaxCardsDrawnException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null , " You cant draw any other card");
			}
		}
	}
	
	
	public static void main (String []args) {
		Window w = new Window() ;
		
	}
	}


	