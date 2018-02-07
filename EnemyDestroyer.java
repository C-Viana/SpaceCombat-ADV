import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//INIMIGO LENTO E RESISTENTE
public class EnemyDestroyer extends GameObjectB implements Runnable {
	Random random = new Random();
	private String enemyYellow = "/EnemyDestroyer.png";
	
	private Handler handler;
	private BufferedImage enemy;
	private int MAXSPEED; //DEFINE O LIMITE PARA VARIAÇÃO DE VELOCIDADE GERADA EM RANDOM
	private int trigger;
	
	public EnemyDestroyer(Handler handler, int x, int y, int enemyLife, ID id, int MAXSPEED) throws IOException {
		super(x, y, enemyLife, id);
		this.MAXSPEED = MAXSPEED;
		this.handler = handler;
		enemy = ImageIO.read(getClass().getResource(enemyYellow)); //CARREGA IMAGEM DO INIMIGO
		velocidadeY = random.nextInt(MAXSPEED) + 1;
	}

	@Override
	

	
	public void tick() {
		y += velocidadeY;
		
		if(y > Game.HEIGHT + 160){
			
			y = -enemy.getHeight();
			x = random.nextInt(Game.WIDTH-60);
		}
		
		trigger = random.nextInt(150);
		if((trigger == 1 || trigger == 10 || trigger == 100) && (y+enemy.getHeight() > 0)){
			try{
				handler.addObj(new ShotDestroyer(handler, Game.vfx, x, y+enemy.getHeight()-10, ID.DestroyerShot, ShotDestroyer.destroyerShot, MAXSPEED+8));
				handler.addObj(new ShotDestroyer(handler, Game.vfx, x+enemy.getWidth()-5, y+enemy.getHeight()-10, ID.DestroyerShot, ShotDestroyer.destroyerShot, MAXSPEED+8));
			}catch(IOException ioe){ ioe.printStackTrace(); }
		}
		
		if(y < -160){
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
		return new Rectangle(x, y, enemy.getWidth()-10, enemy.getHeight());
	}




	@Override
	public void run() {
		handler.addObj(this);
	}
	
	
	
	
}
