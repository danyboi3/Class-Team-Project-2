package edu.cuny.brooklyn.cisc3120.project.game;

public class GameGameBoardArea extends GameBoardArea {
	private GameBoard board;
	private boolean visible;

	public GameGameBoardArea(int width, int height, GameBoard board, boolean visible) {
		super(width, height);

		this.board = board;
		this.visible = visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String[] getPrintedBoard() {
		String[] board = new String[this.height];

		for (int i = 0; i < this.height; ++i) {
			board[i] = "";

			for (int j = 0; j < this.width; ++j) {
				board[i] += this.getPrintableChar(i, j);
			}
		}

		return board;
	}

	private char getPrintableChar(int x, int y) {
		if (!this.visible) {
			return ' ';
		}

		return (char) this.board.getBoard()[x][y];
	}
}
