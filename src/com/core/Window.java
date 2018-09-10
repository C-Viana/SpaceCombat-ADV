package com.core;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


//CRIA A TELA DE JOGO APENAS
public class Window extends Canvas{
	
	private static final long serialVersionUID = -3998529197592789977L;
	
	public Window(int w, int h, Game game){
		
		game.setPreferredSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame();
		
		frame.add(game);
		
		frame.setTitle("SpaceCombatAdventure ALPHA 0.9.1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		
		game.start();
	}
	
	
	
}