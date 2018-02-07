import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//ESTE INIMIGO TENDE AO CENTRO DA TELA ENQUANTO DESCE
public class EnemySmart extends GameObjectB implements Runnable {
	Random random = new Random();
	private String enemyIMG = "/EnemySmart.png";
	
	private BufferedImage enemy;
	private int MAXSPEED; //DEFINE O LIMITE PARA VARIAÇÃO DE VELOCIDADE GERADA EM RANDOM
	private Handler handler;
	private int center, trigger;
	
	public EnemySmart(Handler handler, int x, int y, int enemyLife, ID id, int MAXSPEED) throws IOException {
		super(x, y, enemyLife, id);
		this.MAXSPEED = MAXSPEED;
		this.handler = handler;
		enemy = ImageIO.read(getClass().getResource(enemyIMG)); //CARREGA IMAGEM DO INIMIGO
		
		center = enemy.getWidth()/2;
	}
	
	
	
	
	@Override
	public void tick() {
		
		y += random.nextInt(MAXSPEED) + 1;
		
		if(x > Game.WIDTH/2)
			x--;
		else if(x < Game.WIDTH/2)
			x++;
		
		if(y > Game.HEIGHT + 160){
			y = -160;
			y += random.nextInt(MAXSPEED) + 1;
			x = random.nextInt(Game.WIDTH-60);
		}
		
		if(y<0){
			for(int i=0; i<handler.object.size(); i++){
				if(handler.object.get(i)!=this)
					Collision.collision(this, handler.object.get(i));
			}
		}
		
		trigger = random.nextInt(150);
		if(trigger / 7 == 1){
			try{
				handler.addObj(new ShotEnemy(handler, x+center, y+enemy.getHeight(), ID.Enemy, ShotEnemy.enemyShot, MAXSPEED+5));
			}catch(IOException ioe){ ioe.printStackTrace(); }
		}
		
	}
	
	

	@Override
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
