import java.util.ArrayList;
import java.util.List;

public class SmarterComputer extends Computer {

    public SmarterComputer() {
        super();
    }

    @Override
    public List<Character> guessCode(char char1, char char2) {
        List<ArrayList<Character>> allPossibleAnswers = getAllAnswers(char1, char2);

    }

    private List<ArrayList<Character>> getAllAnswers(char char1, char char2) {
        List<ArrayList<Character>> allPossibleAnswers = new ArrayList<>();

        for (char firstChar = char1; firstChar <= char2 - char1; firstChar++) {
            for (char secondChar =char1; secondChar <= char2 - char1; secondChar++) {
                if(secondChar == firstChar){
                    continue;
                }
                for (char thirdChar = char1; thirdChar <= char2 - char1; thirdChar++){
                    if (thirdChar == firstChar || thirdChar == secondChar){
                        continue;
                    }
                    for (char fourthChar = char1; fourthChar <= char2 - char1; fourthChar++){
                        if (fourthChar == firstChar || fourthChar == secondChar || fourthChar ==thirdChar){
                            continue;
                        }
                        ArrayList<Character> possibleAnswer = new ArrayList<>();
                        possibleAnswer.add(firstChar);
                        possibleAnswer.add(secondChar);
                        possibleAnswer.add(thirdChar);
                        possibleAnswer.add(fourthChar);
                        allPossibleAnswers.add(possibleAnswer);
                    }
                }

            }
        }

        return allPossibleAnswers;
    }
}
