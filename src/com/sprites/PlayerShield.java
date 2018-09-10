package com.sprites;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.abstracts.GameObject;
import com.abstracts.GameObjectB;
import com.abstracts.GameObjectD;
import com.abstracts.GameObjectE;
import com.audio.SoundThread;
import com.core.Collision;
import com.core.Game;
import com.core.Handler;
import com.enums.ID;



public class PlayerShield extends GameObjectD {
	
	private String playershield = "Sprites/Items/PlayerShield.png";
	private BufferedImage shield;
	private Handler handler;
	private int power;
	private SoundThread sound;
	
	
	public PlayerShield(Handler handler, int x, int y, ID id) throws IOException {
		super(x, y, id);
		this.handler = handler;
		this.power = 150;
		shield = ImageIO.read(new FileInputStream(playershield));
		
	}
	
	
	
	public void tick(){
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject tempPlayer = handler.objectPlayer.get(i);
				if(Game.armor == 1){
					this.x = tempPlayer.getX()-52;
					this.y = tempPlayer.getY()-50;
				}
				else if(Game.armor == 2){
					this.x = tempPlayer.getX()-56;
					this.y = tempPlayer.getY()-45;
				}
				else if(Game.armor == 3){
					this.x = tempPlayer.getX()-40;
					this.y = tempPlayer.getY()-15;
				}
				
				}
		
		for(int i=0; i<handler.object.size(); i++){
			GameObjectB object = handler.object.get(i);
			if(Collision.collision(this, object)){
				power -= 15;
				object.setEnemyLife( (object.getEnemyLife()-4) );
				try{
					Game.vfx.addObj(new ShipExplosion(Game.vfx, object.getX(), object.getY()+15));
				}catch (IOException e) { e.printStackTrace(); }
			}
		}
		
		for(int i=0; i<handler.enemylaser.size(); i++){
			GameObjectE tempLaser = handler.enemylaser.get(i);
			if(tempLaser.getBounds().intersects(this.getBounds())){
				power -= 1;
				handler.delObj(tempLaser);
				sound = new SoundThread(3);
				sound.start();
				if(tempLaser.getId() == ID.DestroyerShot){
					try{
						Game.vfx.addObj(new ShipExplosion(Game.vfx, tempLaser.getX(), tempLaser.getY()+10));
					}catch (IOException e) { e.printStackTrace(); }
				}
			}
		}
		
		if(power <= 0)
			handler.delObj(this);
	}
	
	
	
	public void render(Graphics g){
		g.drawImage(shield, x, y, shield.getWidth(), shield.getHeight(), null);
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, shield.getWidth(), shield.getHeight());
		
	}
	
	
	
	public int getWIDTH() {
		return shield.getWidth();
	}



	public int getHEIGHT() {
		return shield.getHeight();
	}
	
	
}