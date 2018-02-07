

//VERIFICA SE HOUVE COLISÃO ENTRE OS DIFERENTES OBJETOS
public class Collision  {
	private static SoundThread sound;
	Handler handler;
	
	public Collision(Handler handler){
		this.handler = handler;
	}
	
	//COLISÃO ENTRE NAVE DO PLAYER E NAVES INIMIGAS
	public static boolean collision(GameObject player, GameObjectB enemy){
		if(player.getBounds().intersects(enemy.getBounds())){
			Game.hitPoints -= 20; //DANO GERADO AO PLAYER
			return true;
		}
		else
			return false;
	}
	
	//COLISÃO ENTRE ITENS E PLAYER
	public static boolean collision(GameObjectC item, GameObject player){
		if(item.getBounds().intersects(player.getBounds())){
			return true;
		}
		else
			return false;
	}
	
	//COLISÃO ENTRE INIMIGOS E LASERS DO PLAYER
	public static boolean collision(GameObjectD laser, GameObjectB enemy){
		if(laser.getBounds().intersects(enemy.getBounds()))
			return true;
		else
			return false;
	}
	
	
	//COLISÃO ENTRE INIMIGOS NO RESPAWN
	public static boolean collision(GameObjectB enemyA, GameObjectB enemyB){
		if(enemyA.getBounds().intersects(enemyB.getBounds())){
			enemyA.setX(Game.random.nextInt(Game.WIDTH-60));
			return true;
		}
		else
			return false;
	}
	
	
	//COLISÃO ENTRE Player E LASERS DOS INIMIGOS
	public static boolean collision(GameObject player, GameObjectE laser){
		if(laser.getBounds().intersects(player.getBounds())){
			sound = new SoundThread(4);
			sound.start();
			Game.hitPoints -= 10;
			return true;
		}
		else
			return false;
	}
	
	
}
