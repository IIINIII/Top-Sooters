package com.topshooters.gop;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import com.topshooters.Images;

public class Game1 extends JFrame implements MouseInputListener,KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6098738589967074275L;
	Game1Panel game1Panel = null;
	public static double multiplier = 0.5;
	
	public Game1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(800, 800));
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		game1Panel = new Game1Panel();
		getContentPane().add(game1Panel, BorderLayout.CENTER);
		pack();
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		getContentPane().setCursor(blankCursor);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		game1Panel.player.angle = Math.atan2(e.getX()-game1Panel.player.x, e.getY()-game1Panel.player.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		game1Panel.player.angle = Math.atan2(e.getX()-game1Panel.player.x, e.getY()-game1Panel.player.y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			BtnStates.rightClick = true;
			BtnStates.leftClick = false;
		} else if(SwingUtilities.isLeftMouseButton(e)) {
			BtnStates.leftClick = true;
			BtnStates.rightClick = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			BtnStates.rightClick = false;
		} else if(SwingUtilities.isLeftMouseButton(e)) {
			BtnStates.leftClick = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		int x=e.getKeyCode();
		if(x==49) {
			game1Panel.player.prevWeapon = game1Panel.player.weapon;
			game1Panel.player.weapon = 1;
			game1Panel.player.rifle.currentAnimation = game1Panel.player.rifle.idle;
		} else if(x==50) {
			game1Panel.player.prevWeapon = game1Panel.player.weapon;
			game1Panel.player.weapon = 2;
			game1Panel.player.handgun.currentAnimation = game1Panel.player.handgun.idle;
		} else if(x==51) {
			game1Panel.player.prevWeapon = game1Panel.player.weapon;
			game1Panel.player.weapon = 3;
			game1Panel.player.knife.currentAnimation = game1Panel.player.knife.idle;
		} else if(x==87) {
			BtnStates.forward = true;
			BtnStates.backward = false;
//			BtnStates.leftward = false;
//			BtnStates.rightward = false;
		} else if(x==82) {
			BtnStates.reload = true;
		} else if(x==65) {
			BtnStates.leftward = true;
			BtnStates.rightward = false;
//			BtnStates.backward = false;
//			BtnStates.forward = false;
		} else if(x==83) {
			BtnStates.backward = true;
			BtnStates.forward = false;
//			BtnStates.leftward = false;
//			BtnStates.rightward = false;
		} else if(x==68) {
			BtnStates.rightward = true;
			BtnStates.leftward = false;
//			BtnStates.backward = false;
//			BtnStates.forward = false;
		} else if(x==16) {
			BtnStates.shift = true;
		} else if(x==37) {
			BtnStates.leftturn = true;
			BtnStates.rightturn = false;
		} else if(x==39) {
			BtnStates.rightturn = true;
			BtnStates.leftturn = false;
		} else if(x==32) {
			BtnStates.leftClick = true;
			BtnStates.rightClick = false;
		} else if(x==69) {
			BtnStates.rightClick = true;
			BtnStates.leftClick = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int x=e.getKeyCode();
		if(x==87) {
			BtnStates.forward = false;
		} else if(x==65) {
			BtnStates.leftward = false;
		} else if(x==83) {
			BtnStates.backward = false;
		} else if(x==68) {
			BtnStates.rightward = false;
		} else if(x==16) {
			BtnStates.shift = false;
		} else if(x==32) {
			BtnStates.leftClick = false;
		}  else if(x==69) {
			BtnStates.rightClick = false;
		} else if(x==37) {
			BtnStates.leftturn = false;
		} else if(x==39) {
			BtnStates.rightturn = false;
		}
	}

}
