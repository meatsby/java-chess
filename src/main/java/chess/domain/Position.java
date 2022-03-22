package chess.domain;

public class Position {

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int ASCII_ALPHABET = 97;
    private static final int ASCII_NUMBER = 49;
    private static final int CHAR_POSITION_LENGTH = 2;
    private static final int RANGE = 7;
    private static final String CHAR_POSITION_LENGTH_EXCEPTION_MESSAGE = "좌표는 알파벳 소문자 하나와 숫자 하나여야 합니다.";
    private static final String CHAR_POSITION_ALPHABET_EXCEPTION_MESSAGE = "알파벳은 a 부터 h 까지만 가능합니다.";
    private static final String CHAR_POSITION_NUMBER_EXCEPTION_MESSAGE = "숫자는 1 부터 8 까지만 가능합니다.";

    private final String charPosition;
    private final int x;
    private final int y;

    public Position(String charPosition) {
        validate(charPosition);
        this.charPosition = charPosition;
        this.x = charPosition.charAt(FIRST_INDEX) - ASCII_ALPHABET;
        this.y = charPosition.charAt(SECOND_INDEX) - ASCII_NUMBER;
    }

    private void validate(String charPosition) {
        validateLength(charPosition);
        validateAlphabetRange(charPosition);
        validateNumberRange(charPosition);
    }

    private void validateLength(String charPosition) {
        if (charPosition.length() != CHAR_POSITION_LENGTH) {
            throw new IllegalArgumentException(CHAR_POSITION_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    private void validateAlphabetRange(String charPosition) {
        if (charPosition.charAt(FIRST_INDEX) < ASCII_ALPHABET
                || charPosition.charAt(FIRST_INDEX) > ASCII_ALPHABET + RANGE) {
            throw new IllegalArgumentException(CHAR_POSITION_ALPHABET_EXCEPTION_MESSAGE);
        }
    }

    private void validateNumberRange(String charPosition) {
        if (charPosition.charAt(SECOND_INDEX) < ASCII_NUMBER
                || charPosition.charAt(SECOND_INDEX) > ASCII_NUMBER + RANGE) {
            throw new IllegalArgumentException(CHAR_POSITION_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public String getCharPosition() {
        return charPosition;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
