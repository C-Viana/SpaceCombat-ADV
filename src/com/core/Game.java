package com.core;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.abstracts.GameObjectB;
import com.audio.MusicBackGround;
import com.audio.SoundThread;
import com.enums.ID;
import com.enums.STATE;
import com.player.Player;
import com.sprites.ShipExplosion;
import com.sprites.Shot;


//CLASSE PRINCIPAL. MAIN THREAD
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 3500690755445622389L;
	
	public static Random random = new Random();
	
	//OBJETOS DO JOGO
	private Handler handler;
	public static FXHandler vfx;
	public static Player player;
	private Spawner spawner;
	private HUD hud;
	private SoundThread sound;
	private MusicBackGround music;
	private Menu menu;
	private Camera camera;
	
	
	//ATRIBUTOS BÁSICOS
	public static Graphics g;
	public final static int WIDTH = 1100, HEIGHT = WIDTH/16*9;
	public static int enemyCount = 3, enemyDestroyed = 0, pontos = 0, fase = 1, weaponType = 1, armor = 1;
	public static int lifeBar = 450, hitPoints = lifeBar;
	public static boolean trigger = true, pause = false;
	public static STATE gameState = STATE.Menu;
	
	
	private boolean running = false;
	private BufferedImage background;
	
	private Thread GameThread;
	
	
	//CONSTRUTOR
	public Game() {
		
		this.init();
	}
	
	
	
	//INICIALIZADOR DE OBJETOS E THREADS SECUNDÁRIAS
	public void init() {
		new Window(WIDTH, HEIGHT, this);
		handler = new Handler();
		vfx = new FXHandler();
		hud = new HUD(this, lifeBar);
		menu = new Menu(handler, vfx);
		
		addMouseListener(menu);
		addKeyListener(new KeyControls(this, handler));
		camera = new Camera(0,-4000.0f+HEIGHT);
		
		try {
			handler.addObj(player = new Player(WIDTH/2, HEIGHT/2, ID.Player));
			background = ImageIO.read(new FileInputStream("Sprites/Backgrounds/BackGround1.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		music = new MusicBackGround();
		music.start();
		
		
		
	}
	
	
	
	//INICIALIZADOR DO JOGO (THREAD PRINCIPAL)
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		GameThread = new Thread(this);
		
		
		GameThread.start();
		
	}
	
	
	
	//FINALIZADOR DO JOGO
	public synchronized void stop() {
		if(!running)
			return;
		
		try{
			GameThread.join();
			spawner.join();
			music.join();
			
			running = false;
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//GAME LOOP: O FLUXO O JOGO É ATUALIZADO E MANTIDO POR ESTE ALGORITMO
	public void run() {
		
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0, frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
		
	}
	
	
	
	//ATUALIZADOR DE AÇÕES DE/ENTRE OBJETOS
	public void tick() {
		
		
		if(gameState == STATE.Game && !pause){
			handler.tick();
			hud.tick();
			vfx.tick();
			
			//INSTÂNCIA TEMPORÁRIA DE INIMIGO PARA CHECAR COLISÃO COM PLAYER 
			for(int i=0; i<handler.object.size(); i++){
				GameObjectB object = handler.object.get(i);
				if(Collision.collision(player, object)){
					if(armor == 1){
						weaponType = 1;
					}
					else if(armor == 2){
						weaponType = 2;
					}
					else if(armor == 3){
						weaponType = 3;
					}
					
					try{
						vfx.addObj(new ShipExplosion(vfx, object.getX(), object.getY()));
						
					}catch(IOException ioe){ioe.printStackTrace();}
					
					object.setEnemyLife( (object.getEnemyLife()-1) );
					
					}
					if(object.getEnemyLife() <= 0){
						handler.delObj(object);
						pontos += (5+HUD.min)*fase;
				}
			}
			
			//CONTROLE DO VALOR DA VIDA DO PLAYER ENTRE 0-MAX
			if(hitPoints <= 0){
				hitPoints = 0;
				weaponType = 1;
				
				try{
					Game.player.playerImg = ImageIO.read(getClass().getResource("Sprites/Player/PlayerAlpha.png"));
				}catch (IOException e) { e.printStackTrace(); }
				camera = new Camera(0,-4000.0f+HEIGHT);
				gameState = STATE.GameOver;
			}
			else if(hitPoints > lifeBar)
				hitPoints = lifeBar;
			
			//ACIONA A THREAD QUE GERA INIMIGOS E AVANÇA DE FASE
			if(enemyCount <= 0){
				
				if(enemyDestroyed >= 10){
					fase++;
				}
				else if(enemyDestroyed >= 20){
					fase++;
				}
				else if(enemyDestroyed >= 30){
					fase++;
				}
				else if(enemyDestroyed >= 50){
					fase++;
				}
				else if(enemyDestroyed >= 60){
					fase++;
				}
				else if(enemyDestroyed >= 80){
					fase++;
				}
				else if(enemyDestroyed >= 90){
					fase++;
				}
				else if(enemyDestroyed >= 100){
					fase++;
				}
				else if(enemyDestroyed >= 110){
					fase++;
				}
				else if(enemyDestroyed >= 120){
					fase++;
				}
				enemyCount = enemyDestroyed/fase;
				spawner = new Spawner(handler);
				spawner.start();
			}
			camera.tick();
		}
		else if(gameState == STATE.GameOver){
			menu.tick();
			player.setX(WIDTH/2);
			player.setY(HEIGHT/2);
		}
		else{
			menu.tick();
		}
		
	}
	
	
	
	//RENDERIZADOR DE TEXTURAS E GRÁFICOS DOS OBJETOS DO JOGO
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, camera.getY()); //beginning of camera
		g.setColor(Color.BLACK);
		
		g.drawImage(background, 0, 0, null);
		g2d.translate(0, -camera.getY()); //ending of camera
		
		
		
		//--------------------------------------------
		
		handler.render(g);
		
		if(gameState == STATE.Game){
			vfx.render(g);
			hud.showDisplay(g);
		}
		else 
			menu.render(g);
		
		//--------------------------------------------
		g.dispose();
		bs.show();
		
	}
	
	
	
	//AÇÕES QUANDO UM BOTÃO ESTÁ PRESSIONADO
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		
			if(key == KeyEvent.VK_W && player.getY() > 0) {
				player.setVelocidadeY(-5); //MOVE PLAYER PARA CIMA
				
			}
			
			else if(key == KeyEvent.VK_S && player.getY()+player.getHEIGHT() < HEIGHT) {
				player.setVelocidadeY(5); //MOVE PLAYER PARA BAIXO
				
			}
			
			else if(key == KeyEvent.VK_A && player.getX() > 0) {
				player.setVelocidadeX(-5); //MOVE PLAYER PARA ESQUERDA
				
			}
			
			else if(key == KeyEvent.VK_D && player.getX()+player.getWIDTH() < WIDTH) {
				player.setVelocidadeX(5); //MOVE PLAYER PARA DIREITA
				
			}
			else if(key == KeyEvent.VK_SPACE) {
				if(pause == false) pause = true;
				else pause = false;
				
			}
			else if(key == KeyEvent.VK_UP && trigger == true){ 
				try { // GERA UM NOVO OBJETO LASER
				if(weaponType == 1){
					handler.addObj(new Shot(player.getX()+(player.getWIDTH()/2), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.blueShot));
				}
				else if(weaponType == 2){
					handler.addObj(new Shot(player.getX(), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.blueInverse));
					handler.addObj(new Shot((player.getX()+(player.getWIDTH()-5)), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.blueInverse));
				}
				else if(weaponType == 3){
					handler.addObj(new Shot(player.getX()+(player.getWIDTH()/2), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.redShot));
				}
				else if(weaponType == 4){
					handler.addObj(new Shot(player.getX()+(player.getWIDTH()/2), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.orangeShot));
				}
				else if(weaponType == 5){
					handler.addObj(new Shot(player.getX()+(player.getWIDTH()/2), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.plasmaRed));
				}
				else if(weaponType >= 6){
					handler.addObj(new Shot(player.getX(), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.yellowShot));
					handler.addObj(new Shot((player.getX()+(player.getWIDTH()-5)), player.getY()+10, ID.PlayerShot, handler, vfx, Shot.yellowShot));
				}
					sound = new SoundThread(1);
					sound.start();
					
					try { sound.join(); } catch (InterruptedException e1) { e1.printStackTrace(); }
				} catch (IOException e1) { e1.printStackTrace(); }
				trigger = false; //IMPEDE JOGADOR DE ATIRAR ENQUANTO BOTÃO ESTIVER PRESSIONADO
			}
		
		
	}
	
	//AÇÕES QUANDO BOTÃO UM É SOLTO
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//PARA A MOVIMENTAÇÃO NA DIREÇÃO CORRESPONDENTE
		if(key == KeyEvent.VK_W) {
			player.setVelocidadeY(0);
			
		}
		
		else if(key == KeyEvent.VK_S) {
			player.setVelocidadeY(0);
			
		}
		
		else if(key == KeyEvent.VK_A) {
			player.setVelocidadeX(0);
			
		}
		
		else if(key == KeyEvent.VK_D) {
			player.setVelocidadeX(0);
			
		}
		
		else if(key == KeyEvent.VK_UP) {
			trigger = true; // LIBERA CONTROLE DE TIRO PARA UM NOVO DISPARO
		}
		
	}
	
	public static void main(String args[]) {
		
		new Game();
		
	}
	
	
	
}