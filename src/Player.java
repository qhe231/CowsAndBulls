
import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.*;

public class Player {
    public DigitalModifier digitalModifier;
    public Computer computer;
    public char firstChar;
    public char secondChar;
    public static final int TURNS = 7;
    public static int turn;


    public Player() {
        digitalModifier = new DigitalModifier();
        turn = 1;
    }

    private void start() {
        boolean numberMode = chooseMode();
        if (numberMode) {
            firstChar = '0';
            secondChar = '9';
        } else {
            firstChar = 'A';
            secondChar = 'F';
        }
        List<Character> playerCode = getPlayerCode();
        boolean chooseSmarterComputer = chooseComputer();
        if (chooseSmarterComputer) {
            computer = new SmarterComputer(firstChar, secondChar);
        } else {
            computer = new Computer(firstChar, secondChar);
        }
        List<Character> computerCode = computer.generateCode(firstChar, secondChar);
        action(playerCode, computerCode);
    }

    private boolean chooseMode() {
        System.out.println("Please choose the game mode: \n 1. number 0-9 \n 2. letter A-F");
        try {
            int mode = Integer.parseInt(Keyboard.readInput());
            if (mode < 1 || mode > 2) {
                throw new CodeFormatException();
            } else {
                return mode == 1;
            }

        } catch (NumberFormatException | CodeFormatException e) {
            System.out.println("Wrong choice!");
            return chooseMode();
        }
    }


    private List<Character> getPlayerCode() {

        System.out.println("Please enter your secret code:");
        String wholePlayerCode = Keyboard.readInput();
        System.out.println("- - -");

        try {

            List<Character> playerCode = digitalModifier.generateList(wholePlayerCode);
            checkCodeFormat(playerCode);
            return playerCode;
        } catch (CodeFormatException e) {
            if (firstChar == '0') {
                System.out.println("Your code needs to be 4 different digits from 0 – 9");
            } else {
                System.out.println("Your guess needs to be 4 different digits from A – F");
            }
            return getPlayerCode();
        }

    }

    private void checkCodeFormat(List<Character> playerCode) throws CodeFormatException {
        for (Character digit : playerCode) {
            if (digit < firstChar || digit > secondChar) {
                throw new CodeFormatException();
            }
        }
    }

    private boolean chooseComputer() {
        System.out.println("Please choose the difficulty level: \n 1. easy \n 2. hard");
        try {
            int mode = Integer.parseInt(Keyboard.readInput());
            if (mode < 1 || mode > 2) {
                throw new CodeFormatException();
            } else {
                return mode == 2;
            }

        } catch (NumberFormatException | CodeFormatException e) {
            System.out.println("Wrong choice!");
            return chooseComputer();
        }
    }

    private void action(List<Character> playerCode, List<Character> computerCode) {
        List<Character> playerGuessCode = playerGuess();
        List<Character> computerGuessCode = computer.guessCode(firstChar, secondChar);
        beat(playerCode, computerCode, playerGuessCode, computerGuessCode);
    }

    public List<Character> playerGuess() {
        System.out.print("You guess: ");
        String playerGuessCode = Keyboard.readInput();
        try {
            List<Character> guess = digitalModifier.generateList(playerGuessCode);
            checkCodeFormat(guess);
            return guess;

        } catch (CodeFormatException e) {
            if (firstChar == '0') {
                System.out.println("Your code needs to be 4 different digits from 0 – 9");
            } else {
                System.out.println("Your guess needs to be 4 different digits from A – F");
            }
            return playerGuess();
        }
    }

    private void beat(List<Character> playerCode, List<Character> computerCode,
                      List<Character> playerGuessCode, List<Character> computerGuessCode) {

        boolean youWin = computer.getCowsAndBulls(computerCode, playerGuessCode, 1);
        if (youWin) {
            System.out.println("You win! :)");
            return;
        }


        String computerCodeString = digitalModifier.generateString(computerGuessCode);
        System.out.println("\n" + "Computer guess: " + computerCodeString);

        boolean computerWins = computer.getCowsAndBulls(computerGuessCode, playerCode, 2);
        if (computerWins) {
            System.out.println("Computer wins! :(");
        } else if (turn == TURNS) {
            System.out.println("Nobody wins! :(");
        } else {
            turn++;
            System.out.println("- - -");
            action(playerCode, computerCode);
        }
    }


    public static void main(String[] args) {
        new Player().start();
    }
}
