package edu.cuny.brooklyn.cisc3120.project.game;

import org.apache.commons.cli.*;

public class TargetGameLauncher {
	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();

		int height = 25;
		int width = 80;

		options.addOption("h", "window-height", true, "Used to set the height of the game's board.");
		options.addOption("w", "window-width", true, "Used to set the width of the game's board.");

		try {
			CommandLine parsed = parser.parse(options, args);

			if (parsed.hasOption("h") && parsed.getOptionValue("h").matches("^\\d+$")) {
				height = Integer.parseInt(parsed.getOptionValue("h"));
			}
			if (parsed.hasOption("w") && parsed.getOptionValue("w").matches("^\\d+$")) {
				width = Integer.parseInt(parsed.getOptionValue("w"));
			}
		} catch (ParseException exception) {
			// do nothing on parse error
		}

		TargetGame game = new TargetGame(height, width);

		game.play();
	}
}
