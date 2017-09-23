package CISC3120.GuessGame;
import com.googlecode.lanterna.TextColor;   //добавляем поддержку цвета
//В игре просто system.out заменен на объект printer
public class GuessGame {

   Player p1;
   Player p2;
   Player p3;
   
   public void startGame(Printer printer) {
       p1 = new Player();
       p2 = new Player();
       p3 = new Player();
       int guessp1 = 0;
       int guessp2 = 0;
       int guessp3 = 0;
       boolean p1isRight = false;
       boolean p2isRight = false;
       boolean p3isRight = false;
       int targetNumber = (int) (Math.random() * 10);
       printer.println("I'm thinking of a number between 0 and 9...");
       while(true) {
           printer.println("Number to guess is " + targetNumber);
           
           p1.guess(printer);
           p2.guess(printer);
           p3.guess(printer);
           
           guessp1 = p1.number;
           printer.println("Player one guessed " + guessp1,TextColor.ANSI.RED);
           guessp2 = p2.number;
           printer.println("Player two guessed " + guessp2,TextColor.ANSI.GREEN);
           guessp3 = p3.number;
           printer.println("Player three guessed " + guessp3,TextColor.ANSI.BLUE);
           
           if (guessp1 == targetNumber) {
               p1isRight = true;
           }
           if (guessp2 == targetNumber) {
               p2isRight = true;
           }
           if (guessp3 == targetNumber) {
               p3isRight = true;
           }
           
           if (p1isRight || p2isRight || p3isRight)
           {
               printer.println("We have a winner!",TextColor.ANSI.YELLOW);
               printer.println("Player one got it right? " + p1isRight);
               printer.println("Player two got it right? " + p2isRight);
               printer.println("Player three got it right? " + p3isRight);
               printer.println("Game is over",TextColor.ANSI.CYAN);
               break;
           }
           else
           {
               printer.println("Players will have to try again.");
           }
       }
   }
}
