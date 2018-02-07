import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//INIMIGO RAPIDO E FORTE QUE SEGUE O JOGADOR
public class EnemyCutter extends GameObjectB implements Runnable {
	
	Random random = new Random();
	private String enemyYellow = "/EnemyCutter.png";
	private Handler handler;
	private BufferedImage enemy;
	private int MAXSPEED; //DEFINE O LIMITE PARA VARIAÇÃO DE VELOCIDADE GERADA EM RANDOM
	private int center, trigger;
	
	public EnemyCutter(Handler handler, int x, int y, int enemyLife, ID id, int MAXSPEED) throws IOException {
		super(x, y, enemyLife, id);
		this.MAXSPEED = MAXSPEED;
		this.handler = handler;
		enemy = ImageIO.read(getClass().getResource(enemyYellow)); //CARREGA IMAGEM DO INIMIGO
		velocidadeY = MAXSPEED;
		center = enemy.getWidth()/2;
	}

	@Override
	

	
	public void tick() {
		y += velocidadeY;
		
		trigger = Game.random.nextInt(150);
		if(trigger / 7 == 1){
			try{
				handler.addObj(new ShotEnemy(handler, x+center, y+enemy.getHeight(), ID.Enemy, ShotEnemy.enemyShot, MAXSPEED+8));
			}catch(IOException ioe){ ioe.printStackTrace(); }
		}
		
		if(y > Game.HEIGHT + 160){
			
			y = -160;
			velocidadeY = random.nextInt(MAXSPEED) + MAXSPEED/2;
			x = random.nextInt(Game.WIDTH-60);
		}
		
		if(y<0){
			for(int i=0; i<handler.object.size(); i++){
				if(handler.object.get(i)!=this)
					Collision.collision(this, handler.object.get(i));
			}
		}
		
	}
	
	
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
