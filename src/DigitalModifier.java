import java.util.ArrayList;
import java.util.List;

public class DigitalModifier {


    public List<Character> generateList(String code) throws CodeFormatException {

        if (code.length() != 4){
            throw new CodeFormatException();
        }

        List<Character> playerCode = new ArrayList<>();

        char digi1 = code.charAt(0);
        char digi2 = code.charAt(1);
        char digi3 = code.charAt(2);
        char digi4 = code.charAt(3);

        playerCode.add(digi1);
        playerCode.add(digi2);
        playerCode.add(digi3);
        playerCode.add(digi4);

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (playerCode.get(i) == playerCode.get(j)) {
                    throw new CodeFormatException();
                }
            }
        }

        return playerCode;
    }


    public String generateString(List<Character> code) {
        String codeString = "";
        for (char digit : code) {
            codeString += digit;
        }

        return codeString;
    }
}
