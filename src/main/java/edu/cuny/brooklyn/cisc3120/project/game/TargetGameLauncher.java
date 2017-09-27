package edu.cuny.brooklyn.cisc3120.project.game;

import org.apache.commons.cli.*;

public class TargetGameLauncher {
	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();

		int height = 10;
		int width = 25;
		boolean startAtLevel1 = true;
		int gameColumn = 0;
		int gameRow = 0;

		options.addOption("h", "window-height", true, "Used to set the height of the game's board.");
		options.addOption("w", "window-width", true, "Used to set the width of the game's board.");
		options.addOption("l", "level", true, "Used to skip to level 2.");
		options.addOption("d", "game-display", true, "Used to set the position of the game board (top-left, top-right, bottom-left, bottom-right)");

		try {
			CommandLine parsed = parser.parse(options, args);

			if (parsed.hasOption("h") && parsed.getOptionValue("h").matches("^\\d+$")) {
				height = Integer.parseInt(parsed.getOptionValue("h"));
			}
			if (parsed.hasOption("w") && parsed.getOptionValue("w").matches("^\\d+$")) {
				width = Integer.parseInt(parsed.getOptionValue("w"));
			}
			if(parsed.hasOption("l")) {
				startAtLevel1 = !parsed.getOptionValue("l").equals("2");
			}
			if(parsed.hasOption("d") && parsed.getOptionValue("d").matches("^(?:top|bottom)-(?:left|right)$")) {
				String[] positions = parsed.getOptionValue("d").split("-");

				gameColumn = positions[0].equals("top") ? 0 : 1;
				gameRow = positions[1].equals("left") ? 0 : 1;
			}
		} catch (ParseException exception) {
			// do nothing on parse error
		}

		TargetGame game = new TargetGame(width, height, startAtLevel1, gameColumn, gameRow);

		game.play();
	}
}
