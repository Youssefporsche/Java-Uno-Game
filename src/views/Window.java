package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Game;
import engine.Player;


public class Window extends JFrame  implements ActionListener {
	private ArrayList<Player> players = new ArrayList<Player>();	
	private Game game;
	
	private JFrame window;
	private JFrame submition;
	
	
	private JPanel left;
	private JPanel right;
	private JPanel up;
	private JPanel down;

	
	private JTextField Submition1;
	private JTextField Submition2;
	private JTextField Submition3;
	private JTextField Submition4; 
	
	private JTextField Submition_left;
	private JTextField Submition_right;
	private JTextField Submition_up;
	private JTextField Submition_down;
	
	private JButton draw;
	private JButton add1;
	private JButton add2;
	private JButton add3;
	private JButton add4;
	private JButton play;
	
	
	Window(){

			
//		---------------------------------------------------------------------------------------------------------------------
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
		
		Submition_left= new  JTextField();
		Submition_left.setPreferredSize(new Dimension(100,100));
		Submition_left.setFont(new Font("arial",Font.BOLD,13));
		Submition_left.setBackground(Color.blue);
		Submition_left.setCaretColor(Color.black);
		
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
		submition.setTitle("Submition");
		submition.setLayout(new GridLayout(4,1,10,10));
		submition.setPreferredSize(new Dimension(800,500));
		submition.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		
	
		
		left.add(Submition_left);
		right.add(Submition_right);
		up.add(Submition_up);
		down.add(Submition_down);
		window.add(left,BorderLayout.WEST);
		window.add(right,BorderLayout.EAST);
		window.add(up,BorderLayout.NORTH);
		window.add(down,BorderLayout.SOUTH);
		
		
		
		
	
		
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
		play.setEnabled(false);
		play.addActionListener(this);
		
		
		add1=new JButton("set");
		add1.addActionListener(this);
		add1.setFocusable(true);
		add2=new JButton("set");
		add2.setEnabled(false);
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
		
		Player p1 = new Player(Submition1.getText());
		Player p2 = new Player(Submition2.getText());
		Player p3 = new Player(Submition3.getText());
		Player p4 = new Player(Submition4.getText());
		
		
		
	}
	
		

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==add1) {
			Submition_left.setText(Submition1.getText());
			Submition_left.setEditable(false);
			
			add1.setEnabled(false);
			add2.setEnabled(true);
			
		}
		if(e.getSource()==add2){
			Submition_right.setText(Submition2.getText());
			Submition_right.setEditable(false);
			
		add2.setEnabled(false);	
		add3.setEnabled(true);
			play.setEnabled(true);
	
		}
		if(e.getSource()==add3){
			Submition_up.setText(Submition3.getText());
			Submition_up.setEditable(false);
			add3.setEnabled(false);	
			add4.setEnabled(true);
				
			}
		if(e.getSource()==add4){
			Submition_down.setText(Submition4.getText());
			Submition_down.setEditable(false);
			add4.setEnabled(false);
			
			}
		if(e.getSource()==play) {
			submition.dispose();
			window.setVisible(true);
		}
	}
	public static void main (String []args) {
		  new Window();
		  
		
		}

	
}

	