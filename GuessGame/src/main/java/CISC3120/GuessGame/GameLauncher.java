package CISC3120.GuessGame;

public class GameLauncher {

    public static void main(String[] args) {

        Printer printer = new Printer();

        GuessGame game = new GuessGame();
        game.startGame(printer);
        printer.pause();
        printer.close();

    }
}
