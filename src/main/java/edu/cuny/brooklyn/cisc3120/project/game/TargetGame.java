package edu.cuny.brooklyn.cisc3120.project.game;

import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TargetGame {
	private static Logger logger = LoggerFactory.getLogger(TargetGame.class);

	private int GAME_TARGET_AREA_HEIGHT;
	private int GAME_TARGET_AREA_WIDTH;
	private boolean startAtLevel1;

	private GameBoard gameBoard;      // having its own dimension: cells
	private GameDisplay gameDisplay;  // having its own dimension: cells to characters
	private Random rng;

	Scanner scanner;

	public TargetGame(int width, int height, boolean startAtLevel1, int gameColumn, int gameRow) {
		GAME_TARGET_AREA_HEIGHT = height;
		GAME_TARGET_AREA_WIDTH = width;

		gameBoard = new GameBoard(width, height);
		gameDisplay = new GameDisplay(width, height, gameBoard, gameColumn, gameRow);
		rng = new Random();

		this.startAtLevel1 = startAtLevel1;

		this.scanner = new Scanner(System.in);
	}

	void play() {
		boolean won = false;
		int tries = 20;
		setTarget();
		gameDisplay.setPrompt("Enter your target position (x, y):");
		gameDisplay.setVisible(startAtLevel1);
		gameDisplay.setMessages(new String[] {"Tries: " + tries});
		gameDisplay.draw();
		while (!won && tries > 0) {
			gameDisplay.setVisible(tries > 10 && startAtLevel1);

			int xGuess = 0;
			int yGuess = 0;

			boolean valid = false;
			do {
				String guess = gameDisplay.getInput(this.scanner);

				if (guess.matches("^\\d+[\\s,]+\\d+$")) {
					String[] guesses = guess.replaceAll("[^\\d]+", " ").split(" ");

					xGuess = Integer.parseInt(guesses[0]) - 1;
					yGuess = Integer.parseInt(guesses[1]) - 1;

					valid = true;
				} else {
					gameDisplay.setPrompt("Invalid input, please try again. (x, y):");
					gameDisplay.draw();
				}
			} while (!valid);
			logger.debug("Player guessed x = " + xGuess + ", y =" + yGuess + ".");

			--tries;

			gameDisplay.setMessages(new String[] {"Tries: " + tries});

			if (gameBoard.getCell(xGuess, yGuess) == 'X') {
				gameDisplay.setPrompt("You won. Game over.");
				won = true;
			} else if (tries == 0) {
				gameDisplay.setPrompt("You're all out of tries. Game over.");
			} else {
				gameDisplay.setPrompt("Try again. Enter your target position (x, y): ");
			}
			gameDisplay.draw();
		}
	}

	private void setTarget() {
		int x = rng.nextInt(GAME_TARGET_AREA_HEIGHT);
		int y = rng.nextInt(GAME_TARGET_AREA_WIDTH);

		gameBoard.setCell(x, y, 'X');
		logger.debug("Target: " + x + "," + y);
	}

}
