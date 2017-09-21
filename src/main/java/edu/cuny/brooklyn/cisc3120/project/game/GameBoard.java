package edu.cuny.brooklyn.cisc3120.project.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameBoard {
	private static Logger logger = LoggerFactory.getLogger(GameBoard.class);
	private int width;
	private int height;

	int[][] boardCells;

	public GameBoard(int height, int width) {
		this.width = width;
		this.height = height;

		boardCells = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				boardCells[i][j] = ' ';
			}
		}
	}

	public int[][] getBoard() {
		return boardCells;
	}

	public int getCell(int x, int y) {
		if(x >= boardCells.length || boardCells.length == 0 || y >= boardCells[0].length) {
			return 0;
		}

		return boardCells[y][x];
	}

	public void setCell(int x, int y, int target) {
		boardCells[y][x] = target;
	}

	public void plotBorder() {
		for (int i = 0; i < width; i++) {
			boardCells[0][i] = '-';
			boardCells[height - 1][i] = '-';
		}
		for (int i = 0; i < height; i++) {
			boardCells[i][0] = '|';
			boardCells[i][width - 1] = '|';
		}
	}

	public void writeText(int x, int y, String text) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			logger.debug("Text is outside of the displaying area.");
			return;
		}
		for (int i = 0; i < text.length(); i++) {
			if (x + i >= width) {
				logger.debug("Text \"" + text.substring(i) + "\" is outside of the display area.");
				break;
			} else {
				boardCells[y][x + i] = text.charAt(i);
			}
		}
	}
}
