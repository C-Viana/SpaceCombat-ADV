package com.enemies;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.abstracts.GameObjectB;
import com.core.Collision;
import com.core.Game;
import com.core.Handler;
import com.enums.ID;
import com.sprites.ShotEnemy;

//ESTE INIMIGO TENDE AO CENTRO DA TELA ENQUANTO DESCE
public class EnemyFast extends GameObjectB implements Runnable {
	Random random = new Random();
	private String enemyIMG = "Sprites/Enemies/EnemyFast.png";
	
	private BufferedImage enemy;
	private int MAXSPEED, DIFFERENCE = 50; //DEFINE O LIMITE PARA VARIAÇÃO DE VELOCIDADE GERADA EM RANDOM
	private Handler handler;
	private int center, trigger;
	
	public EnemyFast(Handler handler, int x, int y, int enemyLife, ID id, int MAXSPEED) throws IOException {
		super(x, y, enemyLife, id);
		this.MAXSPEED = MAXSPEED;
		this.handler = handler;
		enemy = ImageIO.read(new FileInputStream(enemyIMG)); //CARREGA IMAGEM DO INIMIGO
		
		center = enemy.getWidth()/2;
	}
	
	
	
	
	@Override
	public void tick() {
		
		y += random.nextInt(MAXSPEED) + 8;
		
		if(x > Game.WIDTH/2 && x < Game.WIDTH-DIFFERENCE)
			x--;
		else if(x < Game.WIDTH/2 && x > DIFFERENCE)
			x++;
		
		if(y > Game.HEIGHT + 160){
			y = -160;
			y += random.nextInt(MAXSPEED) + 1;
			x = random.nextInt(Game.WIDTH-60);
		}
		
		trigger = random.nextInt(150);
		if(trigger / 7 == 1){
			try{
				handler.addObj(new ShotEnemy(handler, x+center, y+enemy.getHeight(), ID.Enemy, ShotEnemy.enemyShot, MAXSPEED+5));
			}catch(IOException ioe){ ioe.printStackTrace(); }
		}
		
		if(y<0){
			for(int i=0; i<handler.object.size(); i++){
				if(handler.object.get(i)!=this)
					Collision.collision(this, handler.object.get(i));
			}
		}
		
	}
	
	

	@Override
	public void render(Graphics g) {
		
		g.drawImage(enemy, x, y, enemy.getWidth(), enemy.getHeight(), null);
		
	}

	//GERA RETÂNGULO PARA IDENTIFICAR COLISÃO
	public Rectangle getBounds() {
		return new Rectangle(x, y, enemy.getWidth(), enemy.getHeight());
	}
	
	
	@Override
	public void run() {
		handler.addObj(this);
	}
	
	
	
}
