package com.topshooters.gop;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.topshooters.Animation;

public class Feet {
	
	public Player owner;
	public Animation idle;
	public Animation run;
	public Animation strafeLeft;
	public Animation strafeRight;
	public Animation walk;
	public Animation currentAnimation;
	public int state;
	
	public Feet(Player owner) {
		this.owner = owner;
		
		idle = new Animation(66, 77);
		run = new Animation(102, 62);
		strafeLeft = new Animation(77, 87);
		strafeRight = new Animation(77, 88);
		walk = new Animation(86, 62);
		
		idle.load("feet/idle", "survivor-idle_");
		run.load("feet/run", "survivor-run_");
		strafeLeft.load("feet/strafe_left", "survivor-strafe_left_");
		strafeRight.load("feet/strafe_right", "survivor-strafe_right_");
		walk.load("feet/walk", "survivor-walk_");

		idle.interuptable = true;
		run.interuptable = true;
		strafeLeft.interuptable = true;
		strafeRight.interuptable = true;
		walk.interuptable = true;
		
		state = -1;
		
		currentAnimation = idle;
	}
	
	public void checkBtnStates() {
		if(currentAnimation.interuptable) {
			if(BtnStates.forward&&!(BtnStates.leftward||BtnStates.rightward)) {
				if(BtnStates.shift) {
					if(currentAnimation != walk) {
						currentAnimation = walk;
						currentAnimation.currentFrame = 0;
					}
				} else {
					if(currentAnimation != run) {
						currentAnimation = run;
						currentAnimation.currentFrame = 0;
					}
				}
				owner.s = 5;
			} else if(BtnStates.backward&&!(BtnStates.leftward||BtnStates.rightward)) {
				if(currentAnimation != walk) {
					currentAnimation = walk;
					currentAnimation.currentFrame = 0;
				}
				owner.s = -5;
			}
			if(BtnStates.leftward) {
				if(currentAnimation != strafeLeft) {
					currentAnimation = strafeLeft;
					currentAnimation.currentFrame = 0;
				}
				owner.s = 5;
				owner.side=1;
			} else if(BtnStates.rightward) {
				if(currentAnimation != strafeRight) {
					currentAnimation = strafeRight;
					currentAnimation.currentFrame = 0;
				}
				owner.s = -5;
				owner.side=1;
			} else {
				owner.side=0;
			}
			if(!(BtnStates.forward|BtnStates.backward|BtnStates.leftward|BtnStates.rightward)) {
				if(currentAnimation != idle) {
					currentAnimation = idle;
					currentAnimation.currentFrame = 0;
				}
			}
			if(BtnStates.leftturn) {
				owner.angle+=0.1;
			} else if(BtnStates.rightturn) {
				owner.angle-=0.1;
			}
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
		if(currentAnimation == run)
			owner.run();
		if(currentAnimation != idle)
			owner.walk();
	}
}
