package edu.cuny.brooklyn.cisc3120.project.game;

import java.util.Scanner;

public class GameDisplay {
	private GameGameBoardArea gameArea;
	private StatsGameBoardArea statsArea;
	private InputGameBoardArea inputArea;

	private int width;
	private int height;

	private int gameRow;
	private int gameColumn;

	public GameDisplay(int width, int height, GameBoard gameboard, int gameRow, int gameColumn) {
		this.width = width * 2 + 3;
		this.height = height + 4;

		this.gameArea = new GameGameBoardArea(width, height, gameboard, true);
		this.statsArea = new StatsGameBoardArea(width, height, new String[0]);
		this.inputArea = new InputGameBoardArea(this.width - 2, 1, "");

		this.gameRow = gameRow;
		this.gameColumn = gameColumn;
	}

	public void setVisible(boolean visible) {
		this.gameArea.setVisible(visible);
	}

	public void setMessages(String[] messages) {
		this.statsArea.setMessages(messages);
	}

	public void setPrompt(String prompt) {
		this.inputArea.setPrompt(prompt);
	}

	public String getInput(Scanner scanner) {
		return scanner.nextLine().trim();
	}

	void draw() {
		String[] game = gameArea.getPrintedBoard();
		String[] stats = statsArea.getPrintedBoard();
		String[] input = inputArea.getPrintedBoard();

		String board = this.getSpacer(this.width);

		if (this.gameRow == 1) {
			board += '#' + input[0] + "#\n" + this.getSpacer(this.width);
		}

		for (int i = 0; i < this.height - 4; ++i) {
			if (gameColumn == 0) {
				board += '#' + game[i] + '#' + stats[i] + "#\n";
			} else {
				board += '#' + stats[i] + '#' + game[i] + "#\n";
			}
		}

		if (this.gameRow == 0) {
			board += this.getSpacer(this.width) + '#' + input[0] + "#\n";
		}

		board += this.getSpacer(this.width);

		System.out.print(board);
	}

	private String getSpacer(int width) {
		return String.format("%0" + width + "d", 0).replace('0', '#') + '\n';
	}
}
