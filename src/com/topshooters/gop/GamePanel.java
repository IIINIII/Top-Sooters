package com.topshooters.gop;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.topshooters.Animation;
import com.topshooters.Images;

public class GamePanel extends JComponent implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9068260370753242437L;
	
	public Player player = new Player(400, 400);
	
	public GamePanel() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(this, 0, 48L, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		AffineTransform affineTransform = new AffineTransform();
		Graphics2D graph = (Graphics2D) g;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setTransform(affineTransform);
		graph.translate(player.x, player.y);
		graph.rotate(-player.angle+Math.PI/2);
		graph.scale(0.5, 0.5);
		ImageObserver imgob = new ImageObserver() {
			
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		graph.drawImage(player.bottom_part.frames.get(player.bottom_part.currentFrame), -player.bottom_part.x, -player.bottom_part.y, imgob);
		graph.drawImage(player.top_part.frames.get(player.top_part.currentFrame), -player.top_part.x, -player.top_part.y, imgob);
		player.bottom_part.next();
		player.top_part.next();
		if(player.top_part == Images.rifle_reload && player.top_part.currentFrame == 0) {
			player.top_part = Images.rifle_idle;
			player.top_part.currentFrame = 0;
		} else if(player.top_part == Images.handgun_reload && player.top_part.currentFrame == 0) {
			player.top_part = Images.handgun_idle;
			player.top_part.currentFrame = 0;
		}
		if(player.bottom_part == Images.feet_run) {
			player.run();
		} else if(player.bottom_part != Images.feet_idle) {
			player.walk();
		}
		super.paint(g);
	}

}
