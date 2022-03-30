package chess.domain.state;

import chess.domain.board.Rank;
import chess.domain.piece.Position;

public interface BoardState {

    boolean isEnd();

    boolean isBlackTurn();

    Winner findWinner();

    BoardState move(Position start, Position target);

    Rank getRank(int rankLine);

    BoardState terminate();

    double calculateBlackScore();

    double calculateWhiteScore();

    End judgeWinner();

    Playing judgeTurn();

    String findTurn();
}