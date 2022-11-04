package com.mygdx.game1;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class DesktopLauncher {
	
	// A standard libgdx class for running the application on Desktop
	// "Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument"
	
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("");
		config.setWindowedMode(600, 650);
		config.setResizable(false);
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
