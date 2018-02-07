import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

//THREAD PARA EFEITOS SONOROS
public class SoundThread extends Thread{
	public static final String shotSound = "/Laser Blast.wav", 
								explosionA = "/explosion-b.wav", 
								laserhit = "/laser hit.wav", 
								damagehit = "/laser damage.wav";  
	
	private int control = 0;
	
	public static Mixer mixer;
	public static Clip clip;
	
	public SoundThread(int control){
		this.control = control;
	}
	
	
	static Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
	
	
	public static void play(String path){
		mixer = AudioSystem.getMixer(mixInfos[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try{
			
			clip = (Clip)mixer.getLine(dataInfo);
			
		}catch(LineUnavailableException lue){ lue.printStackTrace(); }
		
		
		try{
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundThread.class.getResource(path));
			clip.open(audioStream);
			
		}catch(LineUnavailableException lue){ lue.printStackTrace(); }
		catch(UnsupportedAudioFileException uafe){ uafe.printStackTrace(); }
		catch(IOException ioe){ ioe.printStackTrace(); }
		
		clip.start();
		
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
