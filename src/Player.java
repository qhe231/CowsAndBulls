import java.util.*;

public class Player {
    public DigitalModifier digitalModifier;
    public Computer computer;

    public Player() {
        digitalModifier = new DigitalModifier();
        computer = new Computer();
    }

    private void start() {
        List<Character> playerCode = getPlayerCode();


    }

    private List<Character> getPlayerCode() {

        System.out.println("Please enter your secret code:");
        String wholePlayerCode = Keyboard.readInput();
        try {
            int codeInt = Integer.parseInt(wholePlayerCode);
            if (codeInt > 9999 || codeInt < 1000) {
                throw new CodeFormatException();
            }
            List<Character> playerCode = digitalModifier.generateList(wholePlayerCode);
            return playerCode;

        } catch (NumberFormatException | CodeFormatException e) {
            System.out.println("Your code needs to be 4 different digits from 0 – 9");
            return getPlayerCode();
        }

    }


    public static void main(String[] args) {
        new Player().start();
    }
}
