package com.topshooters.gop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class Bullet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3876509988976339562L;
	int r;
	double x,y;
	boolean isShown;
	Motion motion;
	Player owner;
	
	public Bullet(int x, int y, double force, int theta, Player owner) {
		this.x=x;
		this.y=y;
		isShown=true;
		this.owner = owner;
		this.r=3;
		this.motion = new Motion(force, theta);
	}
	
	public void paint(Graphics2D graph, AffineTransform affineTransform) {
		if(isShown==false) return;
		graph.setTransform(affineTransform);
		graph.translate(x, y);
		graph.scale(0.5, 0.5);
		graph.setColor(Color.decode("#ffffff"));
		graph.fillOval(-this.r, -this.r, this.r*2, this.r*2);
	}
	
	public void move() {
		if(motion.getTIA()==300) isShown=false;
		if(isShown==false) return;
		//motion.refresh();
		motion.setTIA(motion.getTIA()+1);
		x += motion.getVx();
		y += motion.getVy();
		//if((x<0&&motion.getVx()<0)||(x>Game.width&&motion.getVx()>0)) motion.setVx(-motion.getVx());
	}

	public boolean isIntersecting(int x1, int y1, int x2, int y2) {
		if(x>x1 && x<x2 && y>y1 && y<y2) return true;
		return false;
	}
	
}
