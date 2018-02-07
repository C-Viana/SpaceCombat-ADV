import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class ShipExplosion {
	
	private String imagePath = "/Explosion115.png";
	
	Random random = new Random();
	private FXHandler vfx;
	private BufferedImage image;
	private BufferedImage[] frames;
	private int size = 17, counter = 0, difference = 4, index = 0, x, y;
	
	
	public ShipExplosion(FXHandler vfx, int x, int y) throws IOException {
		this.x = x;
		this.y = y;
		this.vfx = vfx;
		this.frames = new BufferedImage[size];
		
		image = ImageIO.read(getClass().getResource(imagePath)); 
		
		for(int i=0; i<size; i++){
			frames[i] = image.getSubimage((i*115), 0, 115, 115);
		}
		
		image = frames[0];
	}

	

	
	public void tick() {
		
		counter++;
		if(counter > difference){
			index++;
			difference += 4;
			image = frames[index];
			if(index > frames.length-2){
				/*index=0;
				counter = 0;
				difference = 4;*/
				vfx.delObj(this);
				
			}
				
		}
		
		if(y > Game.HEIGHT){
			y = -80;
		}
		
	}
	
	
	public void render(Graphics g) {
		
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
	}

	
	
	
	
}
