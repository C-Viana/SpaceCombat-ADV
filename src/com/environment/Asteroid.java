package com.environment;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.abstracts.GameObjectB;
import com.core.Game;
import com.core.Handler;
import com.enums.ID;


public class Asteroid extends GameObjectB implements Runnable {
	
	private String imagePath = "Sprites/Asteroids/Asteroids2000x64.png";
	
	Random random = new Random();
	private Handler handler;
	private BufferedImage image;
	private BufferedImage[] frames;
	private int MAXSPEED, size = 30, counter = 0, difference = 10, index = 0;
	
	
	public Asteroid(Handler handler, int x, int y, int enemyLife, ID id, int MAXSPEED) throws IOException {
		super(x, y, enemyLife, id);
		this.MAXSPEED = MAXSPEED;
		this.handler = handler;
		this.frames = new BufferedImage[size];
		
		image = ImageIO.read(new FileInputStream(imagePath)); 
		
		for(int i=0; i<size; i++){
			frames[i] = image.getSubimage(i*64, 0, 64, 64);
		}
		velocidadeY = random.nextInt(MAXSPEED) + MAXSPEED/2;
	}

	

	
	public void tick() {
		
		y += velocidadeY;
		
		counter++;
		if(counter > difference){
			index++;
			difference += 10;
			image = frames[index];
			if(index > frames.length-2){
				index=0;
				counter = 0;
				difference = 10;
				image = frames[index];
			}
				
		}
		
		if(y > Game.HEIGHT){
			y = -80;
			velocidadeY = random.nextInt(MAXSPEED) + 1;
			x = random.nextInt(Game.WIDTH-64)+64;
		}
		
	}
	
	
	public void render(Graphics g) {
		
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
	}

	//GERA RETÂNGULO PARA IDENTIFICAR COLISÃO
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(), image.getHeight());
	}




	@Override
	public void run() {
		handler.addObj(this);
	}
	
	
	
	
}
