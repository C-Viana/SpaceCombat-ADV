import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//CLASSE PARA OS TIROS LASER DO INIMIGO
public class ShotEnemy extends GameObjectE {
	
	public static String enemyShot = "/EnemyLaserShot.png";
	private Handler handler;
	private BufferedImage shotImg;
	private int velocity;
	
	
	public ShotEnemy(Handler handler, int x, int y, ID id, String path, int velocity) throws IOException {
		super(x, y, id);
		this.handler = handler;
		shotImg = ImageIO.read(getClass().getResource(path));
		this.velocity = velocity;
	}
	

	@Override
	public void tick() {
		
		y += velocity;
		
		if(y >= Game.HEIGHT){
			handler.delObj(this);
		}
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject tempPlayer = handler.objectPlayer.get(i);
				
				if(Collision.collision(tempPlayer, this)){
					handler.delObj(this);
				}
			}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(shotImg, x, y, shotImg.getWidth(), shotImg.getHeight(), null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, shotImg.getWidth(), shotImg.getHeight());
	}
	
	
	
}
