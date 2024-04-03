package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MainClass;

import java.util.*;

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
		String[] splittedArguments = (input.split("-"));


		if (splittedArguments[0].startsWith("run")) {
			String operating;
			for (int i = 1; i < splittedArguments.length; i++) {
				if (splittedArguments[i].startsWith("seed::")) {
					operating = splittedArguments[i].split("::")[1];
					if (operating.split(" ")[0].equals("random")) {
						MainClass.seedOffset= new Random().nextInt(0,100000);
					} else {
						MainClass.seedOffset= Integer.parseInt(operating.split(" ")[0]);
					}
					System.out.println("Seed set to "+MainClass.seedOffset);
				} else if (splittedArguments[i].startsWith("wsize::")) {
					operating = splittedArguments[i].split("::")[1];
					MainClass.worldSize= Integer.parseInt(operating);
					System.out.println("Worldsize set to "+operating.split(" ")[0]);
				} else if (splittedArguments[i].startsWith("default")) {
					System.out.println("Launching with default values");
					break;
				}
			}
		} else if (splittedArguments[0].startsWith("exit")) {
			System.exit(0);
		}
		else {
			System.out.println("You input could not be validated. This run will be terminated");
			System.exit(0);

		}
	}
}
