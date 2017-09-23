/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CISC3120.GuessGame;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 *
 * @author
 */
//  Console Printer class 
public class Printer {

    private Terminal terminal;  // bottom layer - terminal
    private Screen screen;      //  current layer, display
    private TerminalSize ts;    // terminal size
    private TerminalPosition tp;    // current cursor position

    // building 
    public Printer() {
        try {
            
            terminal = new DefaultTerminalFactory().createTerminal(); // Creating terminal
            
            screen = new TerminalScreen(terminal); // Creating terminal screen
            
            screen.startScreen();  // working with that screen
            
            screen.clear();  // cleaning the screen
            
            tp = new TerminalPosition(0, 0);  // beginning position of cursor 
            
            screen.setCursorPosition(tp); // setting this position
             
            ts = screen.getTerminalSize(); // setting terminal size in memory
        } catch (IOException e) {   // catching exceptions
            e.printStackTrace();
        }
    }
    
// Printing symbol with standard color 
    private void printchar(char ch) {
        
        printchar(ch, TextColor.ANSI.DEFAULT);  // Printing out from reloaded method
    }
// Printing symbols with requested color 
    private void printchar(char ch, TextColor col) {
        if (ch == '\n') {   // if symbol "line skip"
            tp = tp.withColumn(0);  // then setting column to 0
            tp = tp.withRelativeRow(1); // and starting from a new line
        } else {    // for any other symbols
            // creating symbol by requested color 
            TextCharacter tc = new TextCharacter(ch, col, TextColor.ANSI.DEFAULT);
            // setting it to current cursor position 
            screen.setCharacter(tp, tc);
            // moving cursor to the right
            tp = tp.withRelativeColumn(1);
            // if cursor touch first board of the terminal
            if (tp.getColumn() >= ts.getColumns()) {
                tp = tp.withColumn(0); //  setting current column to 0
                tp = tp.withRelativeRow(1); // and starting from the new line
            }
        }
        //  if get to the low board of the terminal
        if (tp.getRow() >= ts.getRows()) {
            // scrolling
            screen.scrollLines(0, ts.getRows() - 1, 1);
            // and adjusting cursor row
            tp = tp.withRow(ts.getRows() - 1);
        }
        //  setting cursor position 
        screen.setCursorPosition(tp);
    }
// printing row with standard color
    public void print(String s) {
        // printing out with reloaded method
        print(s, TextColor.ANSI.DEFAULT);
    }
//  printing row with with needed color 
    public void print(String s, TextColor col) {
        try {
            // printing by each symbol all row with needed color
            for (int i = 0; i < s.length(); i++) {
                printchar(s.charAt(i), col);
            }
            screen.refresh();
        } catch (IOException e) {   //  catching exceptions
            e.printStackTrace();
        }
    }
// Printing the row with standard color
public void println(String s) {
    // printing with reloaded method
    println(s,TextColor.ANSI.DEFAULT);
}
// printing row with changed color
    public void println(String s, TextColor col) {
        print(s + "\n",col);
    }
// waiting to press any button
    public void pause() {
        try {
            screen.readInput(); // press the button
        } catch (IOException e) {   //  catching exceptions
            e.printStackTrace();
        }
    }
//  stop working with the screen
    public void close() {
        try {
            screen.stopScreen();    // finishing working with the screen
        } catch (IOException e) {   // catching exceptions
            e.printStackTrace();
        }
    }
}
