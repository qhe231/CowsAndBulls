import java.util.List;

public class NumGetter {

    public int getBulls(List<Character> code, List<Character> guess) {
        int bulls = 0;
        for (int i = 0; i < 4; i++) {
            if (code.get(i) == guess.get(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    public int getCows(List<Character> code, List<Character> guess) {
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            if (code.contains(guess.get(i))){
                cows++;
            }
        }

        return cows;
    }
}
