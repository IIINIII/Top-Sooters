package com.topshooters;

public abstract class Images {
	
	public static Animation feet_idle = new Animation(66, 77);
	public static Animation feet_run = new Animation(102, 62);
	public static Animation feet_strafe_left = new Animation(77, 87);
	public static Animation feet_strafe_right = new Animation(77, 88);
	public static Animation feet_walk = new Animation(86, 62);
	
	public static Animation flashlight_idle = new Animation(93, 105);
	public static Animation flashlight_meleeattack = new Animation(99, 129);
	public static Animation flashlight_move = new Animation(93, 113);
	
	public static Animation handgun_idle = new Animation(96, 120);
	public static Animation handgun_meleeattack = new Animation(103, 124);
	public static Animation handgun_move = new Animation(101, 119);
	public static Animation handgun_reload = new Animation(100, 119);
	public static Animation handgun_shoot = new Animation(98, 119);
	
	public static Animation knife_idle = new Animation(106, 113);
	public static Animation knife_meleeattack = new Animation(103, 113);
	public static Animation knife_move = new Animation(106, 112);
	
	public static Animation rifle_idle = new Animation(94, 120);
	public static Animation rifle_meleeattack = new Animation(114, 201);
	public static Animation rifle_move = new Animation(94, 119);
	public static Animation rifle_reload = new Animation(100, 121);
	public static Animation rifle_shoot = new Animation(93, 119);
	
	public static Animation shotgun_idle = new Animation(94, 120);
	public static Animation shotgun_meleeattack = new Animation(114, 201);
	public static Animation shotgun_move = new Animation(94, 119);
	public static Animation shotgun_reload = new Animation(100, 121);
	public static Animation shotgun_shoot = new Animation(93, 119);
	
	public static boolean load() {
		
		feet_idle.load("feet/idle", "survivor-idle_");
		feet_run.load("feet/run", "survivor-run_");
		feet_strafe_left.load("feet/strafe_left", "survivor-strafe_left_");
		feet_strafe_right.load("feet/strafe_right", "survivor-strafe_right_");
		feet_walk.load("feet/walk", "survivor-walk_");
		
		flashlight_idle.load("flashlight/idle", "survivor-idle_flashlight_");
		flashlight_meleeattack.load("flashlight/meleeattack", "survivor-meleeattack_flashlight_");
		flashlight_move.load("flashlight/move", "survivor-move_flashlight_");

		handgun_idle.load("handgun/idle", "survivor-idle_handgun_");
		handgun_meleeattack.load("handgun/meleeattack", "survivor-meleeattack_handgun_");
		handgun_move.load("handgun/move", "survivor-move_handgun_");
		handgun_reload.load("handgun/reload", "survivor-reload_handgun_");
		handgun_shoot.load("handgun/shoot", "survivor-shoot_handgun_");
		
		knife_idle.load("knife/idle", "survivor-idle_knife_");
		knife_meleeattack.load("knife/meleeattack", "survivor-meleeattack_knife_");
		knife_move.load("knife/move", "survivor-move_knife_");

		rifle_idle.load("rifle/idle", "survivor-idle_rifle_");
		rifle_meleeattack.load("rifle/meleeattack", "survivor-meleeattack_rifle_");
		rifle_move.load("rifle/move", "survivor-move_rifle_");
		rifle_reload.load("rifle/reload", "survivor-reload_rifle_");
		rifle_shoot.load("rifle/shoot", "survivor-shoot_rifle_");

		shotgun_idle.load("shotgun/idle", "survivor-idle_shotgun_");
		shotgun_meleeattack.load("shotgun/meleeattack", "survivor-meleeattack_shotgun_");
		shotgun_move.load("shotgun/move", "survivor-move_shotgun_");
		shotgun_reload.load("shotgun/reload", "survivor-reload_shotgun_");
		shotgun_shoot.load("shotgun/shoot", "survivor-shoot_shotgun_");
		
		return true;
	}
	
}
