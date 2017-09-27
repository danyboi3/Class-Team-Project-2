package edu.cuny.brooklyn.cisc3120.project.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameBoard {
	private static Logger logger = LoggerFactory.getLogger(GameBoard.class);
	private int width;
	private int height;

	int[][] boardCells;

	public GameBoard(int width, int height) {
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
		if (x >= height || height == 0 || y >= width || width == 0) {
			return ' ';
		}

		return boardCells[x][y];
	}

	public void setCell(int x, int y, int target) {
		boardCells[x][y] = target;
	}
}
