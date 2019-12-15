import java.sql.SQLOutput;
import java.util.*;

public class Player {
    public DigitalModifier digitalModifier;
    public Computer computer;
    public static final int TURNS = 7;
    public int turn;


    public Player() {
        digitalModifier = new DigitalModifier();
        turn = 1;

    }

    private void start() {
        List<Character> playerCode = getPlayerCode();
        boolean chooseSmarterComputer = chooseComputer();
        if (chooseSmarterComputer) {
            computer = new SmarterComputer();
        } else {
            computer = new Computer();
        }
        List<Character> computerCode = computer.generateCode();
        action(playerCode, computerCode);
    }


    private List<Character> getPlayerCode() {

        System.out.println("Please enter your secret code:");
        String wholePlayerCode = Keyboard.readInput();
        System.out.println("- - -");

        try {
            checkCodeFormat(wholePlayerCode);
            List<Character> playerCode = digitalModifier.generateList(wholePlayerCode);
            return playerCode;
        } catch (NumberFormatException | CodeFormatException e) {
            System.out.println("Your code needs to be 4 different digits from 0 – 9");
            return getPlayerCode();
        }

    }

    private void checkCodeFormat(String wholePlayerCode) throws CodeFormatException {
        int codeInt = Integer.parseInt(wholePlayerCode);
        if (codeInt > 9999 || codeInt < 0) {
            throw new CodeFormatException();
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
        List<Character> computerGuessCode = computer.guessCode();
        beat(playerCode, computerCode, playerGuessCode, computerGuessCode);
    }

    public List<Character> playerGuess() {
        System.out.print("You guess: ");
        String playerGuessCode = Keyboard.readInput();
        try {
            checkCodeFormat(playerGuessCode);
            List<Character> guess = digitalModifier.generateList(playerGuessCode);
            return guess;

        } catch (NumberFormatException | CodeFormatException e) {
            System.out.println("Your guess needs to be 4 different digits from 0 – 9");
            return playerGuess();
        }
    }

    private void beat(List<Character> playerCode, List<Character> computerCode,
                      List<Character> playerGuessCode, List<Character> computerGuessCode) {

        boolean youWin = computer.getCowsAndBulls(computerCode, playerGuessCode);
        if (youWin) {
            System.out.println("You win! :)");
            return;
        }


        String computerCodeString = digitalModifier.generateString(computerGuessCode);
        System.out.println("\n" + "Computer guess: " + computerCodeString);

        boolean computerWins = computer.getCowsAndBulls(computerGuessCode, playerCode);
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
