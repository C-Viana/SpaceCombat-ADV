import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


//ITEM QUE REPARA DANOS SOFRIDOS PELO PLAYER
public class RepairItem extends GameObjectC {
	
	private String repairIMG = "/RepairIcon.png";
	
	private Handler handler;
	private BufferedImage repairIcon;
	
	
	public RepairItem(int x, int y, ID id, Handler handler) throws IOException  {
		super(x, y, id);
		this.handler = handler;
		
		repairIcon = ImageIO.read(getClass().getResource(repairIMG));
		
	}
	
	

	@Override
	public void tick() {
		
		y += 1;
		
		
		for(int i=0; i<handler.objectPlayer.size(); i++){
			GameObject player = handler.objectPlayer.get(i);
				if(Collision.collision(this, player)){
					Game.hitPoints += 30; //REPARO GERADO AO PLAYER
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
