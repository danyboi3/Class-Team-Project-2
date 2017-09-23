package CISC3120.GuessGame;
//import java.io.IOException;


public class Player {
    int number = 0;
    public void guess(Printer printer)
    {
        number = (int) (Math.random() * 10);
        printer.println("I'm guessing " + number);
    }
}
