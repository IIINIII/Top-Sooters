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

public class Game extends JFrame implements MouseInputListener,KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6098738589967074275L;
	GamePanel gamePanel = null;
	
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(800, 800));
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		gamePanel = new GamePanel();
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		pack();
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		getContentPane().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gamePanel.player.angle = Math.atan2(e.getX()-gamePanel.player.x, e.getY()-gamePanel.player.y);
//		if(SwingUtilities.isRightMouseButton(e)) {
//			if(gamePanel.player.weapon == 1) {
//				gamePanel.player.top_part = Images.rifle_meleeattack;
//				gamePanel.player.top_part.currentFrame = 0;
//			} else if(gamePanel.player.weapon == 2) {
//				gamePanel.player.top_part = Images.handgun_meleeattack;
//				gamePanel.player.top_part.currentFrame = 0;
//			} else if(gamePanel.player.weapon == 3) {
//				gamePanel.player.top_part = Images.knife_meleeattack;
//				gamePanel.player.top_part.currentFrame = 0;
//			}
//			mPressed=true;
//		} else if(SwingUtilities.isLeftMouseButton(e)) {
//			if(gamePanel.player.weapon == 1) {
//				gamePanel.player.top_part = Images.rifle_shoot;
//				gamePanel.player.top_part.currentFrame = 0;
//			} else if(gamePanel.player.weapon == 2) {
//				gamePanel.player.top_part = Images.handgun_shoot;
//				gamePanel.player.top_part.currentFrame = 0;
//			} else if(gamePanel.player.weapon == 3) {
//				gamePanel.player.top_part = Images.knife_meleeattack;
//				gamePanel.player.top_part.currentFrame = 0;
//			}
//			mPressed=true;
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gamePanel.player.angle = Math.atan2(e.getX()-gamePanel.player.x, e.getY()-gamePanel.player.y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean mPressed = false;

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			if(gamePanel.player.weapon == 1) {
				gamePanel.player.top_part = Images.rifle_meleeattack;
				gamePanel.player.top_part.currentFrame = 0;
			} else if(gamePanel.player.weapon == 2) {
				gamePanel.player.top_part = Images.handgun_meleeattack;
				gamePanel.player.top_part.currentFrame = 0;
			} else if(gamePanel.player.weapon == 3) {
				gamePanel.player.top_part = Images.knife_meleeattack;
				gamePanel.player.top_part.currentFrame = 0;
			}
			mPressed=true;
		} else if(SwingUtilities.isLeftMouseButton(e)) {
			if(gamePanel.player.weapon == 1) {
				gamePanel.player.top_part = Images.rifle_shoot;
				gamePanel.player.top_part.currentFrame = 0;
			} else if(gamePanel.player.weapon == 2) {
				gamePanel.player.top_part = Images.handgun_shoot;
				gamePanel.player.top_part.currentFrame = 0;
			} else if(gamePanel.player.weapon == 3) {
				gamePanel.player.top_part = Images.knife_meleeattack;
				gamePanel.player.top_part.currentFrame = 0;
			}
			mPressed=true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(gamePanel.player.weapon == 1) {
			gamePanel.player.top_part = Images.rifle_idle;
			gamePanel.player.top_part.currentFrame = 0;
		} else if(gamePanel.player.weapon == 2) {
			gamePanel.player.top_part = Images.handgun_idle;
			gamePanel.player.top_part.currentFrame = 0;
		} else if(gamePanel.player.weapon == 3) {
			gamePanel.player.top_part = Images.knife_idle;
			gamePanel.player.top_part.currentFrame = 0;
		}
		mPressed=false;
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
		if(e.getKeyChar()=='1') {
			gamePanel.player.weapon=1;
			gamePanel.player.top_part = Images.rifle_move;
			gamePanel.player.top_part.currentFrame = 0;
		} else if(e.getKeyChar()=='2') {
			gamePanel.player.weapon=2;
			gamePanel.player.top_part = Images.handgun_move;
			gamePanel.player.top_part.currentFrame = 0;
		} else if(e.getKeyChar()=='3') {
			gamePanel.player.weapon=3;
			gamePanel.player.top_part = Images.knife_move;
			gamePanel.player.top_part.currentFrame = 0;
		} else if(e.getKeyChar()=='w'||e.getKeyChar()=='s') {
			if(gamePanel.player.bottom_part!=Images.feet_run) {
				gamePanel.player.bottom_part = Images.feet_run;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		} else if(e.getKeyChar()=='a') {
			if(gamePanel.player.bottom_part!=Images.feet_strafe_left) {
				gamePanel.player.bottom_part = Images.feet_strafe_left;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		} else if(e.getKeyChar()=='d') {
			if(gamePanel.player.bottom_part!=Images.feet_strafe_right) {
				gamePanel.player.bottom_part = Images.feet_strafe_right;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		} else if(e.getKeyChar()=='W'||e.getKeyChar()=='S') {
			if(gamePanel.player.bottom_part!=Images.feet_walk) {
				gamePanel.player.bottom_part = Images.feet_walk;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		} else if(e.getKeyChar()=='A') {
			if(gamePanel.player.bottom_part!=Images.feet_strafe_left) {
				gamePanel.player.bottom_part = Images.feet_strafe_left;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		} else if(e.getKeyChar()=='D') {
			if(gamePanel.player.bottom_part!=Images.feet_strafe_right) {
				gamePanel.player.bottom_part = Images.feet_strafe_right;
				gamePanel.player.bottom_part.currentFrame = 0;
			}
		}
		
		if(e.getKeyChar()=='s'||e.getKeyChar()=='S') {
			gamePanel.player.s = -5;
			gamePanel.player.side=0;
			if(!mPressed) {
				if(gamePanel.player.weapon == 1) {
					gamePanel.player.top_part = Images.rifle_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 2) {
					gamePanel.player.top_part = Images.handgun_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 3) {
					gamePanel.player.top_part = Images.knife_move;
					gamePanel.player.top_part.currentFrame = 0;
				}
			}
		} else if(e.getKeyChar()=='w'||e.getKeyChar()=='W') {
			gamePanel.player.s = 5;
			gamePanel.player.side=0;
			if(!mPressed) {
				if(gamePanel.player.weapon == 1) {
					gamePanel.player.top_part = Images.rifle_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 2) {
					gamePanel.player.top_part = Images.handgun_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 3) {
					gamePanel.player.top_part = Images.knife_move;
					gamePanel.player.top_part.currentFrame = 0;
				}
			}
		} else if(e.getKeyChar()=='a'||e.getKeyChar()=='A') {
			gamePanel.player.s = 5;
			gamePanel.player.side=1;
			if(!mPressed) {
				if(gamePanel.player.weapon == 1) {
					gamePanel.player.top_part = Images.rifle_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 2) {
					gamePanel.player.top_part = Images.handgun_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 3) {
					gamePanel.player.top_part = Images.knife_move;
					gamePanel.player.top_part.currentFrame = 0;
				}
			}
		} else if(e.getKeyChar()=='d'||e.getKeyChar()=='D') {
			gamePanel.player.s = -5;
			gamePanel.player.side=1;
			if(!mPressed) {
				if(gamePanel.player.weapon == 1) {
					gamePanel.player.top_part = Images.rifle_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 2) {
					gamePanel.player.top_part = Images.handgun_move;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 3) {
					gamePanel.player.top_part = Images.knife_move;
					gamePanel.player.top_part.currentFrame = 0;
				}
			}
		} else if(e.getKeyChar()=='r'||e.getKeyChar()=='R') {
			if(gamePanel.player.weapon == 1) {
				gamePanel.player.top_part = Images.rifle_reload;
				gamePanel.player.top_part.currentFrame = 0;
			} else if(gamePanel.player.weapon == 2) {
				gamePanel.player.top_part = Images.handgun_reload;
				gamePanel.player.top_part.currentFrame = 0;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar()=='w'||e.getKeyChar()=='s'||e.getKeyChar()=='a'||e.getKeyChar()=='d'||e.getKeyChar()=='W'||e.getKeyChar()=='S'||e.getKeyChar()=='A'||e.getKeyChar()=='D') {
			gamePanel.player.bottom_part = Images.feet_idle;
			gamePanel.player.bottom_part.currentFrame = 0;
			gamePanel.player.s = 0;
			if(!mPressed) {
				if(gamePanel.player.weapon == 1) {
					gamePanel.player.top_part = Images.rifle_idle;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 2) {
					gamePanel.player.top_part = Images.handgun_idle;
					gamePanel.player.top_part.currentFrame = 0;
				} else if(gamePanel.player.weapon == 3) {
					gamePanel.player.top_part = Images.knife_idle;
					gamePanel.player.top_part.currentFrame = 0;
				}
			}
		}
	}

}
