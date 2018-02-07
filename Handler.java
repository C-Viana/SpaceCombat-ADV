import java.util.LinkedList;
import java.awt.Graphics;


//ADMINISTRA AS ACOES DOS ELEMENTOS INTERATIVOS DO JOGO
public class Handler {
	
	private SoundThread sound;
	
	//CADA LISTA ADICIONA UM TIPO DIFERENTE DE OBJETO
	LinkedList<GameObject> objectPlayer = new LinkedList<GameObject>(); //ADICIONA O JOGADOR
	LinkedList<GameObjectB> object = new LinkedList<GameObjectB>(); //ADICIONA OS INIMIGOS
	LinkedList<GameObjectC> item = new LinkedList<GameObjectC>(); //ADICIONA ITENS QUE AUXILIAM O JOGADOR
	LinkedList<GameObjectD> laser = new LinkedList<GameObjectD>(); //ADICIONA CADA LASER GERADO AO ATIRAR
	LinkedList<GameObjectE> enemylaser = new LinkedList<GameObjectE>(); //ADICIONA CADA LASER GERADO PELOS INIMIGOS
	
	
	//ATUALIZA TODOS OS ATRIBUTOS DE POSIÇÃO E ESTADO DOS OBJETOS DAS LISTAS
	public void tick() {
		
		
		for(int i=0; i<laser.size(); i++){
			GameObjectD tempObj = laser.get(i);
			tempObj.tick();
		}
		
		for(int i=0; i<enemylaser.size(); i++){
			GameObjectE tempObj = enemylaser.get(i);
			tempObj.tick();
		}
		
		for(int i=0; i<object.size(); i++){
			GameObjectB tempObj = object.get(i);
			tempObj.tick();
		}
		
		for(int i=0; i<objectPlayer.size(); i++){
			GameObject tempObj = objectPlayer.get(i);
			tempObj.tick();
		}
		
		
		for(int i=0; i<item.size(); i++){
			GameObjectC tempObj = item.get(i);
			tempObj.tick();
		}
		
		
	}
	
	//RENDERIZA TODOS OS OBJETOS DAS LISTAS APÓS SUA ATUALIZAÇÃO
	public void render(Graphics g) {
		
		
		for(int i=0; i<laser.size(); i++){
			GameObjectD tempObj = laser.get(i);
			tempObj.render(g);
		}
		
		for(int i=0; i<enemylaser.size(); i++){
			GameObjectE tempObj = enemylaser.get(i);
			tempObj.render(g);
		}
		
		for(int i=0; i<object.size(); i++){
			GameObjectB tempObj = object.get(i);
			tempObj.render(g);
		}
		
		for(int i=0; i<objectPlayer.size(); i++){
			GameObject tempObj = objectPlayer.get(i);
			tempObj.render(g);
		}
		
		for(int i=0; i<item.size(); i++){
			GameObjectC tempObj = item.get(i);
			tempObj.render(g);
		}
		
		
	}
	
	
	//ADICIONA OBJETOS NA LISTA - PLAYER
	public void addObj(GameObject obj) {
		objectPlayer.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - PLAYER
	public void delObj(GameObject obj) {
		objectPlayer.remove(obj);
	}
	
	
	
	//ADICIONA OBJETOS NA LISTA - INIMIGOS
	public void addObj(GameObjectB obj) {
		object.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - INIMIGOS
	public void delObj(GameObjectB obj) {
		object.remove(obj);
		if(obj.getId() == ID.Enemy){
			Game.enemyCount--;
			Game.enemyDestroyed++;
		}
		sound = new SoundThread(2);
		sound.start();
	}
	
	
	//ADICIONA OBJETOS NA LISTA - ITENS
	public void addObj(GameObjectC obj) {
		item.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - ITENS
	public void delObj(GameObjectC obj) {
		item.remove(obj);
	}
	
	
	//ADICIONA OBJETOS NA LISTA - LASERS
	public void addObj(GameObjectD obj) {
		laser.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - LASERS
	public void delObj(GameObjectD obj) {
		laser.remove(obj);
	}
	
	
	
	//ADICIONA OBJETOS NA LISTA - LASERS
	public void addObj(GameObjectE obj) {
		enemylaser.add(obj);
	}
	
	//REMOVE OBJETOS NA LISTA - LASERS
	public void delObj(GameObjectE obj) {
		enemylaser.remove(obj);
	}
	
}