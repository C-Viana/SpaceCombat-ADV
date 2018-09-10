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
public class ArmorUp extends GameObjectC {
	
	private final String armorIMG = "Sprites/Items/ArmorUpItem.png";
	private final String armorB = "Sprites/Player/PlayerBlue(74x120).png";
	private final String armorC = "Sprites/Player/PlayerTitanium.png";
	private Handler handler;
	private BufferedImage armorIcon;
	
	
	public ArmorUp(int x, int y, ID id, Handler handler) throws IOException  {
		super(x, y, id);
		this.handler = handler;
		
		armorIcon = ImageIO.read(new FileInputStream(armorIMG));
		
	}
	
	

	@Override
	public void tick() {
		
		y += 3;
		
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject player = handler.objectPlayer.get(i);
				if(Collision.collision(this, player)){
					Game.weaponType++;
					handler.delObj(this);
					if(Game.armor == 1){
						try{
							Game.player.playerImg = ImageIO.read(new FileInputStream(armorB));
						}catch (IOException e) { e.printStackTrace(); }
						Game.lifeBar += 150; 
						Game.armor++;
					}
					else if(Game.armor == 2){
						try{
							Game.player.playerImg = ImageIO.read(new FileInputStream(armorC));
						}catch (IOException e) { e.printStackTrace(); }
						Game.lifeBar += 200; 
						Game.armor++;
					}
					else if(Game.armor > 3)
						Game.armor = 3;
					
					Game.hitPoints = Game.lifeBar;
			}
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(armorIcon, x, y, 30, 30, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}
	
	
	
}
