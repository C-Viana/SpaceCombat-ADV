import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;


//THREAD APENAS PARA MÚSICA DE FUNDO DO JOGO
public class MusicBackGround extends Thread{
	
	public static final String gameMusicA = "/Rjuken Sabotage.wav"; //Música da trilha de Medal of Honor (PSX, 1999)
	
	public static Mixer mixer;
	public static Clip clip;
	
	
	static Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
	
	
	public static void play(String path){
		mixer = AudioSystem.getMixer(mixInfos[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{
			
			clip = (Clip)mixer.getLine(dataInfo);
			
		}catch(LineUnavailableException lue){ lue.printStackTrace(); }
		
		
		try{
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(MusicBackGround.class.getResource(path));
			clip.open(audioStream);
			
		}catch(LineUnavailableException lue){ lue.printStackTrace(); }
		catch(UnsupportedAudioFileException uafe){ uafe.printStackTrace(); }
		catch(IOException ioe){ ioe.printStackTrace(); }
		
		clip.start();
		
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
