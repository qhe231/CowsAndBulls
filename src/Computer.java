import java.util.ArrayList;
import java.util.List;

public class Computer {
    DigitalModifier digitalGenerator;

    public Computer() {
        digitalGenerator = new DigitalModifier();
    }

    public List<Character> generateCode() {
        List<Character> computerCode = new ArrayList<>();

        digitalGenerator.generateDigital('0', '9');


        return computerCode;
    }

}
