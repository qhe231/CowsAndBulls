import java.util.List;

public class NumGetter {
    int bulls;
    int cows;

    public int getBulls(List<Character> code, List<Character> guess) {
        bulls = 0;
        for (int i = 0; i < 4; i++) {
            if (code.get(i) == guess.get(i)) {
                bulls++;
            }
        }
        return bulls;
    }

    public int getCows(List<Character> code, List<Character> guess) {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            if (code.contains(guess.get(i))){
                total++;
            }
        }

        cows = total-bulls;

        return cows;
    }
}
