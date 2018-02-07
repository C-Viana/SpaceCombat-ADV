import java.io.IOException;
import java.lang.InterruptedException;

//THREAD PARA GERAR INIMIGOS E MUDAR FASE E DIFICULDADE DO JOGO
public class Spawner extends Thread {
	
	private Handler handler;
	private Thread enemyThread;
	public Spawner(Handler handler){
		this.handler = handler;
	}
	
	
	public void run(){
		try{
			enemySpawn(Game.fase);
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	
	//DE ACORDO COM A FASE, GERA NOVOS INIMIGOS E ITENS
	public void enemySpawn(int fase) throws IOException{
		//DE ACORDO COM ENEMY COUNT, GERA DETERMINADA QUANTIDADE DE INIMIGOS
		switch(fase){
			case 1:
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new Enemy(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 2, ID.Enemy, 4));
					enemyThread.start();
				}
				itemGenerator(Game.random.nextInt());
				break;
				
			case 2:
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new Enemy(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 6));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/Game.fase; i++){
					enemyThread = new Thread(new EnemySmart(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 10));
					enemyThread.start();
				}
				itemGenerator(Game.random.nextInt());
				break;
				
			case 3:
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new Enemy(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/4; i++){
					enemyThread = new Thread(new EnemySmart(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 10));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/Game.fase; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-60)+10, Game.random.nextInt(150)*-1, 1, ID.Enemy, 12));
					enemyThread.start();
				}
				itemGenerator(Game.random.nextInt());
				break;
			case 4:
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new Asteroid(handler, Game.random.nextInt(Game.WIDTH-50)+10, (Game.random.nextInt(150)+64)*-1, 6, ID.Asteroid, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 1, ID.Enemy, 12));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase/2; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 5:
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new EnemyCutter(handler, Game.random.nextInt(Game.WIDTH), Game.random.nextInt(150)*-1, 3, ID.Enemy, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new EnemySmart(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 10));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/Game.fase; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 1, ID.Enemy, 12));
					enemyThread.start();
				}
				handler.addObj(new ArmorUp(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.ArmorUp, handler));
				for(int i=0; i<Game.fase/2; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 6:
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new EnemyCutter(handler, Game.random.nextInt(Game.WIDTH), Game.random.nextInt(150)*-1, 6, ID.Enemy, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new EnemySmart(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 4, ID.Enemy, 10));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 1, ID.Enemy, 12));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase/2; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 7:
				for(int i=0; i<2; i++){
					enemyThread = new Thread(new EnemyDestroyer(handler, Game.random.nextInt(Game.WIDTH), Game.random.nextInt(150)*-1, 30, ID.Enemy, 3));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/6; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 2, ID.Enemy, 12));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase/4; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 8:
				for(int i=0; i<4; i++){
					enemyThread = new Thread(new EnemyDestroyer(handler, Game.random.nextInt(Game.WIDTH-100)+5, Game.random.nextInt(150)*-1, 30, ID.Enemy, 5));
					enemyThread.start();
				}
				for(int i=1; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new EnemyCutter(handler, Game.random.nextInt(Game.WIDTH-5)+5, Game.random.nextInt(150)*-1, 6, ID.Enemy, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 9:
				for(int i=0; i<Game.enemyCount/2; i++){
					enemyThread = new Thread(new Asteroid(handler, Game.random.nextInt(Game.WIDTH-50)+10, (Game.random.nextInt(150)+64)*-1, 6, ID.Asteroid, 6));
					enemyThread.start();
				}
				for(int i=1; i<fase; i++){
					enemyThread = new Thread(new EnemyShield(handler, Game.random.nextInt(Game.WIDTH-5)+5, Game.random.nextInt(150)*-1, 6, ID.Enemy, 8));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/Game.fase; i++){
					enemyThread = new Thread(new EnemySmart(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 6, ID.Enemy, 10));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase/4; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 10:
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new Asteroid(handler, Game.random.nextInt(Game.WIDTH-50)+10, (Game.random.nextInt(150)+64)*-1, 6, ID.Asteroid, 6));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount; i++){
					enemyThread = new Thread(new EnemyFast(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 1, ID.Enemy, 12));
					enemyThread.start();
				}
				handler.addObj(new ArmorUp(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.ArmorUp, handler));
				for(int i=0; i<Game.fase/2; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 11:
				for(int i=0; i<Game.enemyCount/3; i++){
					enemyThread = new Thread(new Asteroid(handler, Game.random.nextInt(Game.WIDTH-50)+10, (Game.random.nextInt(150)+64)*-1, 6, ID.Asteroid, 6));
					enemyThread.start();
				}
				for(int i=0; i<Game.enemyCount/3; i++){
					enemyThread = new Thread(new Enemy(handler, Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(150)*-1, 6, ID.Enemy, 10));
					enemyThread.start();
				}
				for(int i=0; i<Game.fase/4; i++){
					itemGenerator(Game.random.nextInt());
				}
				break;
			case 12:
				try{
				enemyThread = new Thread(new EnemyBoss(handler, Game.WIDTH/2, -150, 400, ID.Enemy));
				enemyThread.sleep(3000);
				enemyThread.start();
				}catch(InterruptedException ie) {ie.printStackTrace();}
				break;
				
			default:
				break;
		}
	}
	
	
	//GERA ITENS AUXILIARES 
	public void itemGenerator(int opt) throws IOException{
		switch(opt%3){
			case 0:
				handler.addObj(new RepairItem(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.RepairItem, handler));
				break;
			case 1:
				handler.addObj(new ShieldItem(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.ShieldItem, handler));
				break;
			case 2:
				handler.addObj(new PowerUp(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.PowerUp, handler));
				break;
			default:
				handler.addObj(new RepairItem(Game.random.nextInt(Game.WIDTH-50)+10, Game.random.nextInt(80)*-1, ID.ArmorUp, handler));
				break;
		}
	}
	
	
	
}
