package views;

import java.util.ArrayList;

import javax.swing.JFrame;

import engine.Game;
import engine.Player;

public class Design  extends JFrame {
	
	
	private Window window;

	public Design() {
		window = new Window();
		this.getContentPane().add(window);
		this.setTitle("UNO_Game");
		this.setSize(1360, 780);
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
		this.getContentPane().setLayout(null);
		
	}
	public static void main (String []args) {
	  new Design();
	
	}
	
}
