package com.sprites;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.abstracts.GameObjectB;
import com.abstracts.GameObjectD;
import com.core.Collision;
import com.core.FXHandler;
import com.core.Game;
import com.core.HUD;
import com.core.Handler;
import com.enums.ID;

//CLASSE PARA OS TIROS LASER DO PLAYER
public class Shot extends GameObjectD {
	
	public static String blueShot = "Sprites/Shots/LaserShotBlue.png", orangeShot = "Sprites/Shots/LaserShotOrange.png", redShot = "Sprites/Shots/LaserShotRed.png",
							blueInverse = "Sprites/Shots/LaserShotBlueInverse.png", 
							plasmaRed = "Sprites/Shots/LaserShotPlasmaRed.png", 
							yellowShot = "Sprites/Shots/LaserShotYellow.png",
							enemyShot = "Sprites/Shots/EnemyLaserShot.png";
	
	private Handler handler;
	private FXHandler vfx;
	private BufferedImage shotImg;
	
	
	public Shot(int x, int y, ID id, Handler handler, FXHandler vfx, String path) throws IOException {
		super(x, y, id);
		this.handler = handler;
		this.vfx = vfx;
		shotImg = ImageIO.read(new FileInputStream(path));
	}
	

	@Override
	public void tick() {
		
		y -= 20;
		
		if(y < 0){
			handler.delObj(this);
		}
		
		//VERIFICA COLISÃO ESPECÍFICA ENTRE INIMIGO E TIRO
		for(int i=0; i<handler.object.size(); i++){
			GameObjectB object = handler.object.get(i);
				
				if(Collision.collision(this, object)){
					object.setEnemyLife( (object.getEnemyLife()-Game.weaponType) ) ;
					handler.delObj(this);
					if(object.getEnemyLife() <= 0){ //VERIFICA SE INIMIGO DEVE SER DESTRUÍDO APÓS COLISÃO
						handler.delObj(object);
						Game.pontos += (15+HUD.min)*Game.fase;
						try{
							vfx.addObj(new ShipExplosion(vfx, object.getX(), object.getY()));
							
						}catch(IOException ioe){ioe.printStackTrace();}
					}
				}
			}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(shotImg, x, y, shotImg.getWidth(), shotImg.getHeight(), null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, shotImg.getWidth(), shotImg.getHeight());
	}
	
	
	
}
