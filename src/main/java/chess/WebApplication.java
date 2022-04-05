package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Position;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static GameState gameState = new WhiteTurn(new Board(BoardInitializer.initBoard()));

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = gameState.toMap();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String[] split = req.body().split("=")[1].split(",");
            Position start = new Position(split[0]);
            Position target = new Position(split[1]);
            try {
                gameState = gameState.move(start, target);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            return "";
        });

        get("/terminate", (req, res) -> {
            gameState = new WhiteTurn(new Board(BoardInitializer.initBoard()));
            return "";
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

