import java.util.ArrayList;
import java.util.List;

public class Computer {
    public DigitalGenerator digitalGenerator;
    public NumGetter numGetter;

    public Computer() {
        digitalGenerator = new DigitalGenerator();
        numGetter = new NumGetter();
    }

    public List<Character> generateCode() {
        List<Character> computerCode = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char digi = digitalGenerator.generateDigital('0', '9');
            if (computerCode.contains(digi)) {
                i--;
            } else {
                computerCode.add(digi);
            }
        }
        return computerCode;
    }

    public List<Character> guessCode(){
        List<Character> computerCode = generateCode();
        return computerCode;
    }

    public boolean getCowsAndBulls(List<Character> code, List<Character> guess) {
        int bulls = numGetter.getBulls(code, guess);
        int cows = numGetter.getCows(code, guess);

        if (bulls <= 1 && cows <= 1) {
            System.out.println("Result: " + bulls + " bull and " + cows + " cow");
        } else if (bulls > 1 && cows <= 1) {
            System.out.println("Result: " + bulls + " bulls and " + cows + " cow");
        } else if (bulls <= 1) {
            System.out.println("Result: " + bulls + " bull and " + cows + " cows");
        } else {
            System.out.println("Result: " + bulls + " bulls and " + cows + " cows");
        }

        return bulls == 4;
    }
}
