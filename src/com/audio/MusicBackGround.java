package com.audio;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


//THREAD APENAS PARA MÚSICA DE FUNDO DO JOGO
public class MusicBackGround extends Thread{
	
	public static final String gameMusicA = "Sounds/Themes/Rjuken Sabotage.wav"; //Música da trilha de Medal of Honor (PSX, 1999)
	
	public static Clip clip; 
	static AudioInputStream stream;
	
	
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
	
	
	public void playMusic(){
		play(gameMusicA);
		
	}
	
	
	public void run(){
		try{
			
			playMusic();
			while(clip.getMicrosecondLength() != clip.getMicrosecondPosition())
			{
			}
			playMusic();
			
			
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	
}
