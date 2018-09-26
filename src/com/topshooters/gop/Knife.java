package com.topshooters.gop;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.topshooters.Animation;

public class Knife {

	public Player owner;
	public Animation idle;
	public Animation meleeattack;
	public Animation move;
	public Animation currentAnimation;
	public BufferedImage img;
	public BufferedImage imgSelected;
	public int state;
	
	public Knife(Player owner) {
		this.owner = owner;
		
		try {
			img = ImageIO.read(Rifle.class.getResourceAsStream("/com/topshooters/images/weapons/knife.png"));
			imgSelected = ImageIO.read(Rifle.class.getResourceAsStream("/com/topshooters/images/weapons/knife_selected.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idle = new Animation(106, 113);
		meleeattack = new Animation(103, 113);
		move = new Animation(106, 112);
		
		idle.load("knife/idle", "survivor-idle_knife_");
		meleeattack.load("knife/meleeattack", "survivor-meleeattack_knife_");
		move.load("knife/move", "survivor-move_knife_");

		idle.interuptable = true;
		move.interuptable = true;
		
		state = -1;
		
		currentAnimation = idle;
	}
	
	public void checkBtnStates() {
		if(BtnStates.leftClick) {
			melee();
		} else if(BtnStates.rightClick) {
			melee();
		} else if(currentAnimation.interuptable) {
			if(BtnStates.reload) {
				BtnStates.reload = false;
			}
			if(BtnStates.forward||BtnStates.backward||BtnStates.leftward||BtnStates.rightward) {
				if(currentAnimation != move) {
					currentAnimation = move;
					currentAnimation.currentFrame = 0;
				}
			} else if(currentAnimation != idle) {
				currentAnimation = idle;
				currentAnimation.currentFrame = 0;
			}
		} else if(currentAnimation == meleeattack && currentAnimation.currentFrame == 0) {
			currentAnimation = idle;
			currentAnimation.currentFrame = 0;
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
	}
}
