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
import com.core.Game;
import com.core.Handler;
import com.enums.ID;


//ITEM QUE MELHORA A ARMA DO PLAYER
public class PowerUp extends GameObjectC {
	
	private String itemIMG = "Sprites/Items/PowerUp1.png";
	
	private Handler handler;
	private BufferedImage itemIcon;
	
	
	public PowerUp(int x, int y, ID id, Handler handler) throws IOException  {
		super(x, y, id);
		this.handler = handler;
		
		itemIcon = ImageIO.read(new FileInputStream(itemIMG));
		
	}
	
	

	@Override
	public void tick() {
		
		y += 1;
		
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject player = handler.objectPlayer.get(i);
				if(Collision.collision(this, player)){
					++Game.weaponType;
					handler.delObj(this);
					
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemIcon, x, y, 30, 30, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}
	
	
	
}
