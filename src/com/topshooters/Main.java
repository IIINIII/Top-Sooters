package com.topshooters;

import java.io.File;
import java.util.ArrayList;

import com.topshooters.gop.Game1;

public class Main {
	public static void main(String[] args) {
		/*String s="knife_idle";
		String s1="knife/idle";
		String s2="survivor-idle_knife_";
		for(int i=0;i<20;i++) {
			System.out.println(s+".add(ImageIO.read(Images.class.getResourceAsStream(\"/com/topshooters/images/"+s1+"/"+s2+i+".png\")));");
		}*/
		if(Images.load()) {
			new Game1();
		}
	}
	public void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	            System.out.println((int)'\\');
	            System.out.println(file.toString());
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
}
