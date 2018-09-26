package com.topshooters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.topshooters.gop.Rifle;

public class Animation {
	
	public ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	public int x = 0;
	public int y = 0;
	public int currentFrame = 0;
	public boolean loop = true;
	public boolean interuptable = false;
	public String folder = "";
	
	public Animation(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void playSound(String file) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(Rifle.class.getResourceAsStream("/com/topshooters/sounds/"+file));
			clip.open(ais);
			clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(String dir, String pref) {
		int i=0;
		do {
			try {
				frames.add(ImageIO.read(getClass().getResourceAsStream("/com/topshooters/images/"+dir+"/"+pref+i+".png")));
				i++;
			} catch (Exception e) {
				break;
			}
		} while(true);
		folder = "/com/topshooters/images/"+dir+"/";
	}
	
	public void next() {
		currentFrame++;
		currentFrame%=frames.size();
		if(currentFrame == 8 && folder.indexOf("meleeattack") != -1 && folder.indexOf("knife") == -1) {
			playSound("PUNCH.wav");
		}
	}
	
	public void prev() {
		currentFrame--;
		currentFrame+=frames.size();
		currentFrame%=frames.size();
	}
}
