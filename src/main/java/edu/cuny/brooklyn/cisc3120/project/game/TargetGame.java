package edu.cuny.brooklyn.cisc3120.project.game;

import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TargetGame {
	private static Logger logger = LoggerFactory.getLogger(TargetGame.class);

	private int GAME_TARGET_AREA_HEIGHT;
	private int GAME_TARGET_AREA_WIDTH;

	GameBoard gameBoard;      // having its own dimension: cells
	GameDisplay gameDisplay;  // having its own dimension: cells to characters
	private Random rng;
	private Scanner in;

	public TargetGame() {
		this(25, 80);
	}

	public TargetGame(int height, int width) {
		GAME_TARGET_AREA_HEIGHT = height;
		GAME_TARGET_AREA_WIDTH = width;

		gameBoard = new GameBoard(height, width);
		gameDisplay = new GameDisplay(gameBoard);
		rng = new Random();
		in = new Scanner(System.in);
		in.useDelimiter("(\\p{javaWhitespace}|,)+");
	}

	public void play() {
		boolean won = false;
		setTarget();
		gameBoard.plotBorder();
		gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "Enter your target position (x, y):");
		while (!won) {
			gameDisplay.draw();

			int xGuess = in.nextInt();
			int yGuess = in.nextInt();
			logger.debug("Player guessed x = " + xGuess + ", y =" + yGuess + ".");
			if (gameBoard.getCell(xGuess, yGuess) == 'X') {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "You won. Game over.");
				won = true;
			} else {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT - 1, "Try again. Enter your target position (x, y): ");
			}
			gameDisplay.draw();
		}
	}

	private void setTarget() {
		int x = rng.nextInt(GAME_TARGET_AREA_WIDTH);
		int y = rng.nextInt(GAME_TARGET_AREA_HEIGHT);
		gameBoard.setCell(x, y, 'X');
		logger.debug("Target: " + x + "," + y);
	}

}
