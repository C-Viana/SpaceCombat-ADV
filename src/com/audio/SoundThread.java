package com.audio;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//THREAD PARA EFEITOS SONOROS
public class SoundThread extends Thread{
	public static final String shotSound = "Sounds/Shots/Laser Blast.wav", 
								explosionA = "Sounds/Explosions/explosion-b.wav", 
								laserhit = "Sounds/Shots/laser hit.wav", 
								damagehit = "Sounds/Shots/laser damage.wav";  
	
	private int control = 0;
	
	public static Clip clip; 
	static AudioInputStream stream;
	
	public SoundThread(int control){
		this.control = control;
	}
	
	
	public static void play(String path){
		
		try{
			
			stream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
		}
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (LineUnavailableException e) { e.printStackTrace(); }
		
		
		
	}
	
	
	//MÉTODO PARA ACIONAR O SOM DO TIRO LASER
	public void playShot(){
		play(shotSound);
	}
	
	public void playShipExplosion(){
		play(explosionA);
		
	}
	
	public void playLaserHit(){
		play(laserhit);
	}
	
	public void playDamageHit(){
		play(damagehit);
	}
	
	//QUANDO ATIVADA, VERIFICA AÇÃO TRANSMITIDA E INICIA O EFEITO CORRESPONDENTE
	public void run(){
		try{
			
			if(control == 1){
				playShot();
			}
			else if(control == 2){
				playShipExplosion();
			}
			else if(control == 3){
				playLaserHit();
			}
			else if(control == 4){
				playDamageHit();
			}
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	
}
