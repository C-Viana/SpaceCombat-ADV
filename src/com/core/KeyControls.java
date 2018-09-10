package com.core;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyControls extends KeyAdapter {
		
		Game game;
		Handler handler;
		
		
		
		public KeyControls(Game game, Handler handler) {
			this.game = game;
			this.handler = handler;
		}
		
		
		
		public void keyPressed(KeyEvent e) {
			game.keyPressed(e);
			
		}
		
		
		public void keyReleased(KeyEvent e) {
			game.keyReleased(e);
			
		}
		

	
	
}
