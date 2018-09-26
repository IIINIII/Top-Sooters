package com.topshooters.gop;

import java.io.Serializable;

public class Motion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7890164377532607072L;

	public static Motion Gravity=new Motion(0,1);
	
	private int Vx,Vy,TIA;
	public Motion(int Vx, int Vy) {
		this.setVx(Vx);
		this.setVy(Vy);
		setTIA(0);
	}
	public Motion(double force, int theta) {
		setVx((int) (force*Math.cos(Math.toRadians(theta))));
		setVy(-(int) (force*Math.sin(Math.toRadians(theta))));
		setTIA(0);
	}
	public Motion() {
		setVx(0);
		setVy(0);
		setTIA(0);
	}
	public int getVx() {
		return Vx;
	}
	public void setVx(int vx) {
		Vx = vx;
	}
	public int getVy() {
		return Vy;
	}
	public void setVy(int vy) {
		Vy = vy;
	}
	public int getTIA() {
		return TIA;
	}
	public void setTIA(int tIA) {
		TIA = tIA;
	}
	public void refresh() {
		Vy += Gravity.getVy();
		if(Vy>10)Vy=10;
	}
	
}
