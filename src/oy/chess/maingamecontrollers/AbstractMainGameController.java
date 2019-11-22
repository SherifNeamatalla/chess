package oy.chess.maingamecontrollers;

import javafx.stage.Stage;
import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public abstract class AbstractMainGameController {

  protected Controller controller;

  protected ChessBoard chessBoard;

  protected Game game;

  public void startGame(Stage primaryStage) {
    this.controller = new Controller();
    this.game = controller.startNewGame();
  };

  public abstract void doMove(BoardCell boardCell);

  public abstract void uploadGame(GameMetaInformation gameMetaInformation);

  public Game getGame() {
    return game;
  };

  public Game startNewGame() {
    this.game = controller.startNewGame();
    return this.game;
  };
}
