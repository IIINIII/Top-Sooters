package com.topshooters.gop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Game1Panel extends JComponent implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9068260370753242437L;
	
	public Player player = new Player(400, 400);
	
	public Game1Panel() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(this, 0, 48L, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.decode("#333333"));
		g.fillRect(0, 0, getWidth(), getHeight());
		AffineTransform affineTransform = new AffineTransform();
		Graphics2D graph = (Graphics2D) g;
		ImageObserver imgob = new ImageObserver() {
			
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		};
//		graph.scale(2, 2);
		if(player.weapon == 1) {
			graph.drawImage(player.rifle.imgSelected, 10, 0, 190, 64, imgob);
			graph.drawImage(player.handgun.img, 210, 0, 64, 64, imgob);
			graph.drawImage(player.knife.img, 280, 0, 64, 64, imgob);
		}
		else if(player.weapon == 2) {
			graph.drawImage(player.rifle.img, 10, 0, 190, 64, imgob);
			graph.drawImage(player.handgun.imgSelected, 210, 0, 64, 64, imgob);
			graph.drawImage(player.knife.img, 280, 0, 64, 64, imgob);
		}
		else if(player.weapon == 3) {
			graph.drawImage(player.rifle.img, 10, 0, 190, 64, imgob);
			graph.drawImage(player.handgun.img, 210, 0, 64, 64, imgob);
			graph.drawImage(player.knife.imgSelected, 280, 0, 64, 64, imgob);
		}
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setTransform(affineTransform);
		graph.translate(player.x, player.y);
		graph.rotate(-player.angle+Math.PI/2);
		graph.scale(Game1.multiplier, Game1.multiplier);
		player.feet.paint(graph);
		if(player.weapon == 1) {
			player.rifle.paint(graph);
		}
		else if(player.weapon == 2) {
			player.handgun.paint(graph);
		}
		else if(player.weapon == 3) {
			player.knife.paint(graph);
		}
		graph.rotate(player.angle-Math.PI/2);
		graph.translate(-360, 200);
		paintBullets(graph, affineTransform);
		super.paint(g);
	}
	
	private void paintBullets(Graphics2D graph, AffineTransform affineTransform) {
		for (Bullet b: player.bullets) {
			b.paint(graph, affineTransform);
			b.move();
		}
	}
}
