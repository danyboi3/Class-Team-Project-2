package edu.cuny.brooklyn.cisc3120.project.game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TargetGame {
	private static Logger logger = LoggerFactory.getLogger(TargetGame.class);

	private int GAME_TARGET_AREA_HEIGHT;
	private int GAME_TARGET_AREA_WIDTH;
	private boolean startAtLevel1;

	GameBoard gameBoard;      // having its own dimension: cells
	GameDisplay gameDisplay;  // having its own dimension: cells to characters
	private Random rng;
	private Scanner in;

	public TargetGame() {
		this(25, 80, true);
	}

	public TargetGame(int height, int width, boolean startAtLevel1) {
		GAME_TARGET_AREA_HEIGHT = height;
		GAME_TARGET_AREA_WIDTH = width;

		gameBoard = new GameBoard(height, width);
		gameDisplay = new GameDisplay(gameBoard);
		rng = new Random();
		in = new Scanner(System.in);
		in.useDelimiter("(\\p{javaWhitespace}|,)+");

		this.startAtLevel1 = startAtLevel1;
	}

	public void play() {
		boolean won = false;
		int tries = 20;
		setTarget();
		gameBoard.plotBorder();
		gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "Enter your target position (x, y):");
		while (!won && tries > 0) {
			gameDisplay.draw(tries > 10 && startAtLevel1);

			int xGuess = 0;
			int yGuess = 0;

			boolean valid = false;
			do {
				String guess = in.nextLine();

				if (guess.matches("^\\d+[\\s,]+\\d+$")) {
					String[] guesses = guess.replaceAll("[^\\d]+", " ").split(" ");

					xGuess = Integer.parseInt(guesses[0]);
					yGuess = Integer.parseInt(guesses[1]);

					valid = true;
				} else {
					System.out.println("Invalid input, please try again. (x, y):");
				}
			} while (!valid);
			logger.debug("Player guessed x = " + xGuess + ", y =" + yGuess + ".");

			--tries;

			if (gameBoard.getCell(xGuess, yGuess) == 'X') {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "You won. Game over.");
				won = true;
			} else if (tries == 0) {
				System.out.println("You're all out of tries. Game over.");
			} else {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "Try again. Enter your target position (x, y): ");
			}
		}

	}

	private void setTarget() {
		int x = rng.nextInt(GAME_TARGET_AREA_WIDTH);
		int y = rng.nextInt(GAME_TARGET_AREA_HEIGHT);
		gameBoard.setCell(x, y, 'X');
		logger.debug("Target: " + x + "," + y);
	}

}
