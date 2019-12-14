public class DigitalGenerator {

    public char generateDigital(char char1, char char2) {
        char digital = (char) ((int) (Math.random() * (char2 - char1 + 1)) + char1);
        return digital;
    }
}
