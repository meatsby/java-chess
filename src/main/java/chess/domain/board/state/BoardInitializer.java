package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInitializer {

    private static final int RANK_CAPACITY = 8;

    public static BoardState initBoard() {
        Map<Integer, Rank> ranks = new HashMap<>(RANK_CAPACITY);
        ranks.put(0, new Rank(List.of(
                Rook.createWhite(new Position("a1")),
                Knight.createWhite(new Position("b1")),
                Bishop.createWhite(new Position("c1")),
                Queen.createWhite(new Position("d1")),
                King.createWhite(new Position("e1")),
                Bishop.createWhite(new Position("f1")),
                Knight.createWhite(new Position("g1")),
                Rook.createWhite(new Position("h1"))
        )));
        ranks.put(1, new Rank(List.of(
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
            ranks.put(i - 1, new Rank(List.of(
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
        ranks.put(6, new Rank(List.of(
                Pawn.createBlack(new Position("a7")),
                Pawn.createBlack(new Position("b7")),
                Pawn.createBlack(new Position("c7")),
                Pawn.createBlack(new Position("d7")),
                Pawn.createBlack(new Position("e7")),
                Pawn.createBlack(new Position("f7")),
                Pawn.createBlack(new Position("g7")),
                Pawn.createBlack(new Position("h7"))
        )));
        ranks.put(7, new Rank(List.of(
                Rook.createBlack(new Position("a8")),
                Knight.createBlack(new Position("b8")),
                Bishop.createBlack(new Position("c8")),
                Queen.createBlack(new Position("d8")),
                King.createBlack(new Position("e8")),
                Bishop.createBlack(new Position("f8")),
                Knight.createBlack(new Position("g8")),
                Rook.createBlack(new Position("h8"))
        )));
        return new WhiteTurn(ranks);
    }

}
