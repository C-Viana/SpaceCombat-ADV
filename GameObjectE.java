import java.awt.Graphics;
import java.awt.Rectangle;


//CARACTERISTICAS BASICAS DOS ELEMENTOS INTERATIVOS DO JOGO -> LASERS
public abstract class GameObjectE {
	
	
	protected int x, y;
	protected ID id;
	
	


	public GameObjectE(int x, int y, ID id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}



	public ID getId() {
		return id;
	}




	public void setId(ID id) {
		this.id = id;
	}
	
	
}