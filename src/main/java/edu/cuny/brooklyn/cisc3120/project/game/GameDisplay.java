package edu.cuny.brooklyn.cisc3120.project.game;

public class GameDisplay {
	private GameBoard gameBoard;

	public GameDisplay(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public void draw(boolean visible) {
		for (int[] row : gameBoard.getBoard()) {
			for (int cell : row) {
				System.out.print(cell == 'X' ? (visible ? 'X' : ' ') : ((char) cell));
			}
			System.out.println();
		}
	}
}
