package com.topshooters.gop;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.topshooters.Animation;

public class Rifle {
	
	public Player owner;
	public Animation idle;
	public Animation meleeattack;
	public Animation move;
	public Animation reload;
	public Animation shoot;
	public Animation currentAnimation;
	public BufferedImage img;
	public BufferedImage imgSelected;
	public int state;
	public int ammo;
	public int ammoLeft;
	public int coolDownTime;
	public Point firePoint;
	
	public Rifle(Player owner) {
		this.owner = owner;
		
		try {
			img = ImageIO.read(Rifle.class.getResourceAsStream("/com/topshooters/images/weapons/ak47.png"));
			imgSelected = ImageIO.read(Rifle.class.getResourceAsStream("/com/topshooters/images/weapons/ak47_selected.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idle = new Animation(94, 120);
		meleeattack = new Animation(114, 201);
		move = new Animation(94, 119);
		reload = new Animation(100, 121);
		shoot = new Animation(93, 119);
		
		idle.load("rifle/idle", "survivor-idle_rifle_");
		meleeattack.load("rifle/meleeattack", "survivor-meleeattack_rifle_");
		move.load("rifle/move", "survivor-move_rifle_");
		reload.load("rifle/reload", "survivor-reload_rifle_");
		shoot.load("rifle/shoot", "survivor-shoot_rifle_");

		idle.interuptable = true;
		move.interuptable = true;
		
		reload.loop = false;
		
		state = -1;
		ammoLeft = 290;
		ammo = 30;
		
		firePoint = new Point(292, 151);
		
		currentAnimation = idle;
	}
	
	public void reloadWeapon() {
		if(ammo+ammoLeft>=30) {
			ammoLeft -= (30-ammo);
			ammo = 30;
		} else {
			ammo = ammo+ammoLeft;
			ammoLeft = 0;
		}
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(Rifle.class.getResourceAsStream("/com/topshooters/sounds/Gun+Reload.wav"));
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
	
	public void checkBtnStates() {
		if((currentAnimation == reload && BtnStates.reload) || ammo == 30)
			BtnStates.reload = false;
		if(BtnStates.leftClick) {
			shoot();
		} else if(BtnStates.rightClick) {
			melee();
		} else if(currentAnimation.interuptable) {
			if(BtnStates.reload) {
				if(currentAnimation != reload && ammoLeft>0) {
					currentAnimation = reload;
					currentAnimation.currentFrame = 0;
					reloadWeapon();
				}
				BtnStates.reload = false;
			} else if(BtnStates.forward||BtnStates.backward||BtnStates.leftward||BtnStates.rightward) {
				if(currentAnimation != move) {
					currentAnimation = move;
					currentAnimation.currentFrame = 0;
				}
			} else if(currentAnimation != idle) {
				currentAnimation = idle;
				currentAnimation.currentFrame = 0;
			}
		} else if((currentAnimation == shoot || currentAnimation == meleeattack) && currentAnimation.currentFrame == 0) {
			currentAnimation = idle;
			currentAnimation.currentFrame = 0;
		}
	}
	
	public void shoot() {
		if(currentAnimation.interuptable) {
			currentAnimation = shoot;
			currentAnimation.currentFrame = 0;
		}
		if(currentAnimation == shoot && currentAnimation.currentFrame == 0) {
			int newX = (int) (owner.x + ((owner.x + (firePoint.x-owner.rifle.shoot.x)*Game1.multiplier)-owner.x)*Math.cos(-owner.angle+Math.PI/2) - ((owner.y + (firePoint.y-owner.rifle.shoot.y)*Game1.multiplier)-owner.y)*Math.sin(-owner.angle+Math.PI/2));
			int newY = (int) (owner.y + ((owner.x + (firePoint.x-owner.rifle.shoot.x)*Game1.multiplier)-owner.x)*Math.sin(-owner.angle+Math.PI/2) + ((owner.y + (firePoint.y-owner.rifle.shoot.y)*Game1.multiplier)-owner.y)*Math.cos(-owner.angle+Math.PI/2));
			if(ammo>0) {
				owner.bullets.add(new Bullet(newX, newY, 100, (int) Math.toDegrees(owner.angle-Math.PI/2), owner));
				ammo--;
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(Rifle.class.getResourceAsStream("/com/topshooters/sounds/Gun+Shot2.wav"));
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
			} else {
				BtnStates.reload = true;
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(Rifle.class.getResourceAsStream("/com/topshooters/sounds/Gun+Empty.wav"));
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
		}
	}
	
	public void melee() {
		if(currentAnimation.interuptable) {
			currentAnimation = meleeattack;
			currentAnimation.currentFrame = 0;
		}
	}
	
	public void paint(Graphics2D graph) {
		checkBtnStates();
		graph.drawImage(currentAnimation.frames.get(currentAnimation.currentFrame), -currentAnimation.x, -currentAnimation.y, new ImageObserver() {
			
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		currentAnimation.next();
		if(currentAnimation.currentFrame == 0 && !currentAnimation.loop) {
			currentAnimation = idle;
			currentAnimation.currentFrame = 0;
		}
	}
}
