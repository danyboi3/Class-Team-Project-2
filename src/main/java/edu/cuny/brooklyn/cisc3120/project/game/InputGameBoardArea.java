package edu.cuny.brooklyn.cisc3120.project.game;

public class InputGameBoardArea extends GameBoardArea {
	private String prompt;

	public InputGameBoardArea(int width, int height, String prompt) {
		super(width, height);

		this.prompt = prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String[] getPrintedBoard() {
		String message = this.prompt.equals("") ? "" : this.prompt.substring(0, Integer.min(this.prompt.length(), this.width) - 1);

		for (int i = message.length(); i < this.width; ++i) {
			message += ' ';
		}

		return new String[]{message};
	}
}
