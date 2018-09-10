package com.enemies;
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
import com.enums.STATE;
import com.sprites.ShotDestroyer;


public class EnemyBoss extends GameObjectB implements Runnable {
	
	private String enemyYellow = "Sprites/Enemies/Enemy Boss RD1.png";
	
	Random random = new Random();
	private Handler handler;
	private BufferedImage enemy;
	private int lifeTotal;
	private int center, trigger;
	private Thread enemyThread;
	
	public EnemyBoss(Handler handler, int x, int y, int enemyLife, ID id) throws IOException {
		super(x, y, enemyLife, id);
		lifeTotal = enemyLife;
		this.handler = handler;
		enemy = ImageIO.read(new FileInputStream(enemyYellow));
		this.velocidadeX = 5;
		center = enemy.getWidth()/2;
	}

	@Override
	

	
	public void tick() {
		if(y < 10)
			y += 5;
		
		x += velocidadeX;
		
		if(x+enemy.getHeight() >= Game.WIDTH || x <= 0){
			velocidadeX *= -1;
		}
		//POR QUE NAO ATIRA?
		trigger = random.nextInt(151);
		if(trigger == 0 || trigger == 10 || trigger == 100 || trigger == 150 || trigger == 1 || trigger == 5 || trigger == 151){
			try{
				handler.addObj(new ShotDestroyer(handler, Game.vfx, x+center, y+50, ID.DestroyerShot, ShotDestroyer.destroyerShot, 8));
				handler.addObj(new ShotDestroyer(handler, Game.vfx, x+10, y+50, ID.DestroyerShot, ShotDestroyer.destroyerShot, 8));
				handler.addObj(new ShotDestroyer(handler, Game.vfx, x+enemy.getWidth()-10, y+50, ID.DestroyerShot, ShotDestroyer.destroyerShot, 10));
				if(enemyLife < lifeTotal/2 && (trigger == 10 || trigger == 100)){
					enemyThread = new Thread(new EnemyFast(handler, x+center-10, y, 1, ID.Enemy, 12));
					enemyThread.start();
				}
			}catch(IOException ioe){ ioe.printStackTrace(); }
		}
		
		if(this.enemyLife <= 0)
			Game.gameState = STATE.GameOver;
	}
	
	
	public void render(Graphics g) {
		g.drawImage(enemy, x, y, enemy.getWidth(), enemy.getHeight(), null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
	}




	@Override
	public void run() {
		handler.addObj(this);
	}
	
	
	
	
}
