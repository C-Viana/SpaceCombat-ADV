package com.core;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.io.IOException;

import com.enums.STATE;

public class Menu extends MouseAdapter {
	
	private Handler handler;
	private FXHandler vfx;
	private Spawner spawner;
	
	public Menu(Handler handler, FXHandler vfx){
		this.handler = handler;
		this.vfx = vfx;
	}
	
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX(), my = e.getY();
		
		if(Game.gameState == STATE.Menu){
			if(mouseOver(mx, my, 350, 280, 400, 80)) {
				Game.gameState = STATE.Game;
				HUD.min = 0; HUD.seg = 0; HUD.mili = 0;
				spawner = new Spawner(handler);
				spawner.start();
			}
			
			else if(mouseOver(mx, my, 350, 390, 400, 80)){
				Game.gameState = STATE.Help;
			}
			
			else if(mouseOver(mx, my, 350, 500, 400, 80)){
				System.exit(1);
			}
		}
		else if(Game.gameState == STATE.Help){
			if(mouseOver(mx, my, 355, 445, 400, 80))
				Game.gameState = STATE.Menu;
				return;
		}
		else if(Game.gameState == STATE.GameOver){
			if(mouseOver(mx, my, 355, 445, 400, 80))
				Game.gameState = STATE.Menu;
				handler.object.clear();
				handler.item.clear();
				handler.laser.clear();
				handler.enemylaser.clear();
				vfx.explosions.clear();
				Game.fase = 1; Game.enemyCount = 3; Game.enemyDestroyed = 0; Game.pontos = 0; 
				Game.lifeBar = 450; Game.hitPoints = Game.lifeBar; Game.armor = 1;
				
				return;
		}
	}
	
	
	
	
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	
	
	
	
	public void tick(){
		
	}
	
	
	
	
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height){
		if((mx > x && mx < x+width)){
			if((my > y && my < y+height))
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
	
	
	
	
	
	public void render(Graphics g){
		
		Font font1 = new Font("arial", 1, 50);
		Font font2 = new Font("Fixedsys", 1, 20);
		
		g.setFont(font1);
		
		
		if(Game.gameState == STATE.Menu){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.WHITE);
			g.drawString("SpaceCombat ADV", 350, 100);
			g.drawString("MENU", 490, 250);
			
			g.drawString("Start", 500, 335);
			g.drawRect(350, 280, 400, 80);
			
			g.drawString("Help!!!", 475, 445);
			g.drawRect(350, 390, 400, 80);
			
			g.drawString("Exit", 510, 555);
			g.drawRect(350, 500, 400, 80);
		}
		else if(Game.gameState == STATE.Help){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.WHITE);
			g.drawString("HELP", 500, 150);
			g.drawString("Back", 500, 500);
			g.drawRect(355, 445, 400, 80);
			
			g.setFont(font2);
			g.drawString("Use WASD keys to move your spacecraft", 180, 250);
			g.drawString("To shot a laser blast use the UP key", 180, 280);
			g.drawString("Dodge every other spacecraft and asteroids to survive", 180, 310);
			g.drawString("Beware your health bar!", 180, 340);
			
		}
		else if(Game.gameState == STATE.GameOver){
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.WHITE);
			g.setFont(font1);
			
			g.drawString("GAME OVER!", 400, 150);
			g.drawString("Back", 500, 500);
			g.drawRect(355, 445, 400, 80);
			
			g.setFont(font2);
			g.drawString("You scored: " + Game.pontos, 150, 250);
			g.drawString("You last till level: " + Game.fase, 150, 280);
			g.drawString("Enemies destroyed: " + Game.enemyDestroyed, 150, 310);
		}
	}
	
	
}
