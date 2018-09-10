package com.sprites;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.abstracts.GameObject;
import com.abstracts.GameObjectC;
import com.core.Collision;
import com.core.Handler;
import com.enums.ID;


//ITEM QUE REPARA DANOS SOFRIDOS PELO PLAYER
public class ShieldItem extends GameObjectC {
	
	private String shieldIMG = "Sprites/Items/ShellUp.png";
	
	private Handler handler;
	private BufferedImage repairIcon;
	
	
	public ShieldItem(int x, int y, ID id, Handler handler) throws IOException  {
		super(x, y, id);
		this.handler = handler;
		
		repairIcon = ImageIO.read(new FileInputStream(shieldIMG));
		
	}
	
	

	@Override
	public void tick() {
		
		y += 2;
		
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject player = handler.objectPlayer.get(i);
				if(Collision.collision(this, player)){
					
					try{
						handler.addObj(new PlayerShield(handler, player.getX(), player.getY(), ID.PlayerShield));
					}catch(IOException ioe){ioe.printStackTrace();}
				handler.delObj(this);	
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(repairIcon, x, y, 30, 30, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}
	
	
	
}
