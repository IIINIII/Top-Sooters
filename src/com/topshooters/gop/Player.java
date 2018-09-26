package com.topshooters.gop;

import java.util.ArrayList;

import com.topshooters.Animation;
import com.topshooters.Images;

public class Player {
	
	public int x,y;
	public double angle = 0;
	public double s=5;
	public int side = 0;
	public int weapon = 1;
	public int prevWeapon = 1;
	public Rifle rifle;
	public Handgun handgun;
	public Knife knife;
	public Feet feet;
	public Animation top_part=Images.rifle_idle, bottom_part=Images.feet_idle;
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public Player(int x, int y) {
		rifle = new Rifle(this);
		handgun = new Handgun(this);
		knife = new Knife(this);
		feet = new Feet(this);
		this.x=x;
		this.y=y;
	}
	
	public void walk() {
		x+=s*Math.cos(-angle+Math.PI/2-side*Math.PI/2);
		y+=s*Math.sin(-angle+Math.PI/2-side*Math.PI/2);
	}
	
	public void run() {
		x+=2*s*Math.cos(-angle+Math.PI/2-side*Math.PI/2);
		y+=2*s*Math.sin(-angle+Math.PI/2-side*Math.PI/2);
	}
	
}
