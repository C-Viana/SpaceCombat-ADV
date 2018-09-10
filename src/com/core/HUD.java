package com.core;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

//MOSTRA E ATUALIZA INFORMAÇÕES NA TELA DE JOGO
public class HUD {
	
	Game game;
	public static int min = 0, seg = 0, mili = 0; 
	
	private int lifeBar, hitPoints;
	
	//CONSTRUTOR
	public HUD(Game game, int lifeBar){
		this.game = game;
		this.lifeBar = lifeBar;
		hitPoints = lifeBar;
	}
	
	
	public void tick(){
		lifeBar = Game.lifeBar;
		hitPoints = Game.hitPoints;
		
		//CONTROLA O TAMANHO DA BARRA DE ENERGIA ENTRE 0-TOTAL
		if(hitPoints > lifeBar)
			hitPoints = lifeBar;
		else if(hitPoints < 0)
			lifeBar = 0;
		
		//CONTADOR DO TEMPO DA PARTIDA
		mili++;
		if(mili > 59){
			mili = 0;
			seg++;
			if(seg > 59){
				seg = 0;
				min++;
			}
		}
		
	}
	
	//RENDERIZA AS INFORMAÇÕES NA TELA
	public void showDisplay(Graphics g){
		//FUNDO DA BARRA DE ENERGIA
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, lifeBar/2, 20);
		//BARRA DE ENERGIA
		g.setColor(Color.GREEN);
		g.fillRect(10, 10, hitPoints/2, 20);
		//CONTORNO DA BARRA DE ENERGIA
		g.setColor(Color.WHITE);
		g.drawRect(10, 10, lifeBar/2, 20);
		
		Font font1 = new Font("fixedsys", 1, 14);
		g.setFont(font1);
		
		//CONTADOR DE TEMPO
		g.setColor(Color.GREEN);
		g.drawString("TIME:  "+ min, Game.WIDTH/2, 20);
		g.drawString(" : "+ seg, (Game.WIDTH/2)+60, 20);
		g.drawString(" : "+ mili, (Game.WIDTH/2)+90, 20);
		
		//DEMAIS INFORMAÇÕES
		g.drawString("Nível: "+ Game.fase, 10, 50);
		
		g.drawString("Inimigos destruídos: "+ Game.enemyDestroyed, 10, 70);
		
		g.drawString("Pontos: "+ Game.pontos, 10, 90);
		g.drawString("Inimigos restantes: "+ Game.enemyCount, 10, 110);
	}
	
	
} 