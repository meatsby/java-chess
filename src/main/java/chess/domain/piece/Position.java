package chess.domain.piece;

import java.util.Objects;

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

    private final int x;
    private final int y;

    public Position(String charPosition) {
        validate(charPosition);
        this.x = charPosition.charAt(FIRST_INDEX) - ASCII_ALPHABET;
        this.y = charPosition.charAt(SECOND_INDEX) - ASCII_NUMBER;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position createNextPosition(Position position, Direction direction) {
        return new Position(
                position.getX() + direction.getXDegree(),
                position.getY() + direction.getYDegree());
    }

    public static Position createNextPosition(Position position, Direction direction, int product) {
        return new Position(
                position.getX() + direction.getXDegree() * product,
                position.getY() + direction.getYDegree() * product);
    }

    public static boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < 8 && position.getY() >= 0 && position.getY() < 8;
    }

    public static int calculateStraightDistance(Position position1, Position position2) {
        return Math.max(Math.abs(position1.getX() - position2.getX()), Math.abs(position1.getY() - position2.getY()));
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}