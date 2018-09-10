package com.player;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.abstracts.GameObject;
import com.core.Game;
import com.enums.ID;



public class Player extends GameObject {
	
	private String playerBlue = "Sprites/Player/PlayerAlpha.png";
	public BufferedImage playerImg;
	
	
	
	
	public Player(int x, int y, ID id) throws IOException {
		super(x, y, id);
		
		playerImg = ImageIO.read( new FileInputStream(playerBlue) );
		
		
	}
	
	
	
	public void tick(){
		
		x += velocidadeX;
		y += velocidadeY;
		
		if(x <= 0 || x+ playerImg.getWidth() > Game.WIDTH)
			velocidadeX = 0;
		else if(y < 0 || y+playerImg.getHeight() > Game.HEIGHT)
			velocidadeY = 0;
		
	}
	
	
	
	public void render(Graphics g){
		
		g.drawImage(playerImg, x, y, playerImg.getWidth(), playerImg.getHeight(), null);
		
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, playerImg.getWidth(), playerImg.getHeight());
		
	}
	
	
	
	
	public int getWIDTH() {
		return playerImg.getWidth();
	}



	public int getHEIGHT() {
		return playerImg.getHeight();
	}
	
	
}