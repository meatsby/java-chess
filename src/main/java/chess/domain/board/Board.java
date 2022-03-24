package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int RANK_CAPACITY = 8;
    private static final String INVALID_MOVEMENT_EXCEPTION_MESSAGE = "이동이 불가능한 위치입니다.";
    private static final String OBSTACLE_EXCEPTION_MESSAGE = "경로에 기물이 존재합니다.";

    private final Map<Integer, Rank> ranks;

    public Board() {
        this.ranks = new HashMap<>(RANK_CAPACITY);
    }

    public void initialize() {
        this.ranks.put(0, new Rank(List.of(
                Rook.createWhite(new Position("a1")),
                Knight.createWhite(new Position("b1")),
                Bishop.createWhite(new Position("c1")),
                Queen.createWhite(new Position("d1")),
                King.createWhite(new Position("e1")),
                Bishop.createWhite(new Position("f1")),
                Knight.createWhite(new Position("g1")),
                Rook.createWhite(new Position("h1"))
        )));
        this.ranks.put(1, new Rank(List.of(
                Pawn.createWhite(new Position("a2")),
                Pawn.createWhite(new Position("b2")),
                Pawn.createWhite(new Position("c2")),
                Pawn.createWhite(new Position("d2")),
                Pawn.createWhite(new Position("e2")),
                Pawn.createWhite(new Position("f2")),
                Pawn.createWhite(new Position("g2")),
                Pawn.createWhite(new Position("h2"))
        )));
        for (int i = 3; i < 7; i++) {
            this.ranks.put(i - 1, new Rank(List.of(
                    new Blank(new Position("a" + i)),
                    new Blank(new Position("b" + i)),
                    new Blank(new Position("c" + i)),
                    new Blank(new Position("d" + i)),
                    new Blank(new Position("e" + i)),
                    new Blank(new Position("f" + i)),
                    new Blank(new Position("g" + i)),
                    new Blank(new Position("h" + i))
            )));
        }
        this.ranks.put(6, new Rank(List.of(
                Pawn.createBlack(new Position("a7")),
                Pawn.createBlack(new Position("b7")),
                Pawn.createBlack(new Position("c7")),
                Pawn.createBlack(new Position("d7")),
                Pawn.createBlack(new Position("e7")),
                Pawn.createBlack(new Position("f7")),
                Pawn.createBlack(new Position("g7")),
                Pawn.createBlack(new Position("h7"))
        )));
        this.ranks.put(7, new Rank(List.of(
                Rook.createBlack(new Position("a8")),
                Knight.createBlack(new Position("b8")),
                Bishop.createBlack(new Position("c8")),
                Queen.createBlack(new Position("d8")),
                King.createBlack(new Position("e8")),
                Bishop.createBlack(new Position("f8")),
                Knight.createBlack(new Position("g8")),
                Rook.createBlack(new Position("h8"))
        )));
    }

    public void move(Position start, Position target) {
        if (getPiece(start).isKnight()) {
            jump(start, target);
            return;
        }
        moveStraight(start, target);
    }

    public void jump(Position start, Position target) {
        Piece selected = getPiece(start);
        Piece targetPiece = getPiece(target);

        if (selected.isMovable(targetPiece)) {
            updateBoard(target, selected, start, targetPiece);
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT_EXCEPTION_MESSAGE);
    }

    public void moveStraight(Position start, Position target) {
        Piece selected = getPiece(start);
        Piece targetPiece = getPiece(target);
        Direction direction = Direction.findDirection(start, target);

        checkPath(start, target, direction);

        if (selected.isMovable(targetPiece)) {
            updateBoard(target, selected, start, targetPiece);
            return;
        }

        throw new IllegalArgumentException(INVALID_MOVEMENT_EXCEPTION_MESSAGE);
    }

    private void updateBoard(Position target, Piece selected, Position start, Piece targetPiece) {
        updatePiece(target, selected);
        updatePiece(start, new Blank(start));
        selected.updatePosition(targetPiece.getPosition());
    }

    private void checkPath(Position start, Position target, Direction direction) {
        if (Position.calculateStraightDistance(start, target) == 1) {
            return;
        }

        Position afterStartTarget = Position.createNextPosition(start, direction);

        for (Position position = afterStartTarget;
             !position.equals(target);
             position = Position.createNextPosition(position, direction)) {
            validateCollision(position);
        }
    }

    private void validateCollision(Position position) {
        if (!getPiece(position).isBlank()) {
            throw new IllegalArgumentException(OBSTACLE_EXCEPTION_MESSAGE);
        }
    }

    private void updatePiece(Position position, Piece piece) {
        ranks.get(position.getY())
                .getPieces()
                .set(position.getX(), piece);
    }

    public Piece getPiece(Position position) {
        return ranks.get(position.getY())
                .getPieces()
                .get(position.getX());
    }

    public Rank getRank(int rankLine) {
        return ranks.get(rankLine);
    }
}
