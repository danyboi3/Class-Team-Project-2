package edu.cuny.brooklyn.cisc3120.project.game;

abstract public class GameBoardArea {
	protected int width;
	protected int height;

	public GameBoardArea(int width, int height) {
		this.width = width;
		this.height = height;
	}

	abstract String[] getPrintedBoard();
}
