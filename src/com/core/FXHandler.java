package com.core;
import java.util.LinkedList;

import com.sprites.ShipExplosion;

import java.awt.Graphics;

public class FXHandler {
	
	LinkedList<ShipExplosion> explosions = new LinkedList<ShipExplosion>(); //ADICIONA AS ANIMAÇÕES DAS EXPLOSÕES
	
	
	public void tick() {
		for(int i=0; i<explosions.size(); i++){
			ShipExplosion tempObj = explosions.get(i);
			tempObj.tick();
		}
	}
	
	
	public void render(Graphics g) {
		for(int i=0; i<explosions.size(); i++){
			ShipExplosion tempObj = explosions.get(i);
			tempObj.render(g);
		}
	}
	
	
	
	//ADICIONA OBJETOS NA LISTA - PLAYER
	public void addObj(ShipExplosion obj) {
		explosions.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - PLAYER
	public void delObj(ShipExplosion obj) {
		explosions.remove(obj);
	}
	
	
	
}