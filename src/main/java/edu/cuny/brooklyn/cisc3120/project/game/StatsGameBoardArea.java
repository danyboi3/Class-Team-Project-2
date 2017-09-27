package edu.cuny.brooklyn.cisc3120.project.game;

public class StatsGameBoardArea extends GameBoardArea {
	private String[] messages;

	public StatsGameBoardArea(int width, int height, String[] messages) {
		super(width, height);

		this.messages = messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public String[] getPrintedBoard() {
		if (this.messages.length == 0) {
			String[] messages = new String[this.height];

			for(int i = 0; i < messages.length; ++i) {
				messages[i] = String.format("%0" + this.width + "d", 0).replace('0', ' ');
			}

			return messages;
		}

		String message = "";

		for (int i = 0; i < this.height; ++i) {
			String current = i % 2 == 0 && i / 2 < this.messages.length ? this.messages[i / 2] : "";

			message += current;

			for (int j = current.length(); j < this.width; ++j) {
				message += ' ';
			}

			message += '\n';
		}

		String[] messages = message.split("\\n");

		if (messages.length < this.height) {
			String[] finalMessages = new String[this.height];

			for (int i = 0; i < messages.length; ++i) {
				finalMessages[i] = messages[i];
			}
			for (int i = finalMessages.length; i < this.height; ++i) {
				finalMessages[i] = String.format("%0" + this.width + "d", 0).replace('0', ' ');
			}

			return finalMessages;
		} else if (messages.length > this.height) {
			String[] finalMessages = new String[this.height];

			for (int i = 0; i < this.height; ++i) {
				finalMessages[i] = messages[i];
			}

			return finalMessages;
		}

		return messages;
	}
}
