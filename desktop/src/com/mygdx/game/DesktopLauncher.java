package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MainClass;

import java.util.Scanner;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setWindowedMode(800,800);
		config.setResizable(false);
		config.setWindowIcon(Files.FileType.Internal,"img.png");
		//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setTitle("Terrain");
		handleStartArguments();

		new Lwjgl3Application(new MainClass(), config);
	}
	public static void handleStartArguments() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("-- Note, that the world-size must be greater or equal to 160, so that the world can be rendered properly on screen--");
		System.out.println("Type in here your start arguments :");
		String input = scanner.nextLine();
		String[] cmds = input.split("-");
		if (cmds[0].startsWith("run")) {
			for (int i = 1; i < cmds.length; i++) {
				if (cmds[i].startsWith("seed:")) {
					MainClass.seedOffset= Integer.parseInt(cmds[i].split(":")[1].split(" ")[0]);
					System.out.println("Seed set to "+cmds[i].split(":")[1]);
				} else if (cmds[i].startsWith("wsize:")) {
					MainClass.worldSize= Integer.parseInt(cmds[i].split(":")[1]);
					System.out.println("Worldsize set to "+cmds[i].split(":")[1].split(" ")[0]);
				} else if (cmds[i].startsWith("default")) {
					System.out.println("Launching with default values");
					break;
				}
			}
		} else if (cmds[0].startsWith("exit")) {
			System.exit(0);
		}
	}
}
