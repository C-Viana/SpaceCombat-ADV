import java.awt.Graphics;
import java.awt.Rectangle;


//CARACTERISTICAS BASICAS DOS ELEMENTOS INTERATIVOS DO JOGO -> ITENS
public abstract class GameObjectC {
	
	
	protected int x, y, velocidadeX, velocidadeY;
	protected ID id;
	
	


	public GameObjectC(int x, int y, ID id) {
		
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




	public int getVelocidadeX() {
		return velocidadeX;
	}




	public void setVelocidadeX(int velocidadeX) {
		this.velocidadeX = velocidadeX;
	}




	public int getVelocidadeY() {
		return velocidadeY;
	}




	public void setVelocidadeY(int velocidadeY) {
		this.velocidadeY = velocidadeY;
	}




	public ID getId() {
		return id;
	}




	public void setId(ID id) {
		this.id = id;
	}
	
	
}