import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmarterComputer extends Computer {
    List<ArrayList<Character>> allPossibleAnswers;
    List<Character> lastGuess;


    public SmarterComputer(char char1, char char2) {
        super(char1, char2);
        allPossibleAnswers = getAllAnswers(char1, char2);
//        allPossibleAnswers = new ArrayList<>();
//        generateAllAnswers(new ArrayList<>());

    }

//    private void generateAllAnswers(ArrayList<Character> answer) {
//        if (answer.size() == 4) {
//            allPossibleAnswers.add(answer);
//        } else {
//            for (char  c = char1; c <= char2; c++){
//                if (!answer.contains(c)){
//                    answer.add(c);
//                    generateAllAnswers(answer);
//                }
//            }
//        }
//    }

    @Override
    public List<Character> guessCode(char char1, char char2) {
        if (lastGuess != null) {
            Iterator<ArrayList<Character>> myIterator = allPossibleAnswers.iterator();
            while (myIterator.hasNext()) {
                boolean keep = getCowsAndBulls(lastGuess, myIterator.next(), 3);
                if (!keep) {
                    myIterator.remove();
                }
            }
        }


        int answersNum = allPossibleAnswers.size();
        int givenAnswerIndex = (int) (Math.random() * answersNum);
        lastGuess = allPossibleAnswers.get(givenAnswerIndex);


        return lastGuess;
    }

    private List<ArrayList<Character>> getAllAnswers(char char1, char char2) {
        List<ArrayList<Character>> allAnswers = new ArrayList<>();

        for (char firstChar = char1; firstChar <= char2; firstChar++) {
            for (char secondChar = char1; secondChar <= char2; secondChar++) {
                if (secondChar == firstChar) {
                    continue;
                }
                for (char thirdChar = char1; thirdChar <= char2; thirdChar++) {
                    if (thirdChar == firstChar || thirdChar == secondChar) {
                        continue;
                    }
                    for (char fourthChar = char1; fourthChar <= char2; fourthChar++) {
                        if (fourthChar == firstChar || fourthChar == secondChar || fourthChar == thirdChar) {
                            continue;
                        }
                        ArrayList<Character> possibleAnswer = new ArrayList<>();
                        possibleAnswer.add(firstChar);
                        possibleAnswer.add(secondChar);
                        possibleAnswer.add(thirdChar);
                        possibleAnswer.add(fourthChar);
                        allAnswers.add(possibleAnswer);
                    }
                }

            }
        }

        return allAnswers;
    }
}
