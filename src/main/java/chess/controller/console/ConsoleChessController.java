package chess.controller.console;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Objects;

public class ConsoleChessController extends ChessController {

    public void run() {
        askPlayGame();
        if (willNotPlay()) {
            return;
        }
        play();
        printScoreIfWanted();
    }

    private void askPlayGame() {
        try {
            OutputView.printStartMessage();
            action(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            askPlayGame();
        }
    }

    private boolean willNotPlay() {
        return Objects.isNull(game);
    }

    private void play() {
        while (game.isNotEnd()) {
            playOneTurn();
        }
    }

    private void playOneTurn() {
        try {
            tryOneTurn();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            playOneTurn();
        }
    }

    private void tryOneTurn() {
        OutputView.display(game.allBoard());
        OutputView.printCurrentPlayer(game.currentPlayer());
        action(InputView.inputCommand());
    }

    private void printScoreIfWanted() {
        OutputView.printWillWatchStatusMessage();
        action(InputView.inputCommand());
    }

    @Override
    public void status() {
        OutputView.printScore(game.score());
    }
}
