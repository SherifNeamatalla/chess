package oy.chess.controller;

import oy.chess.controller.gameinitializing.GameInitializer;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;

public class Controller {

  private Game game;

  private GameMode gameMode;

  public Controller(GameMode gameMode) {

    this.gameMode = gameMode;

    this.game = GameInitializer.getInitializedGame(this.gameMode);
  }

  public Controller(Game game, GameMode gameMode) {
    this.gameMode = gameMode;
    this.game = game;
  }

  public Game startNewGame() {

    this.game = GameInitializer.getInitializedGame(this.gameMode);

    return this.game;
  }

  public Game startNewGame(GameMode gameMode) {

    this.gameMode = gameMode;

    this.game = GameInitializer.getInitializedGame(this.gameMode);

    return this.game;
  }

  public MoveResult doMove(Move move) {

    MoveResult result = MoveMaker.doGetMoveResult(move, game);

    this.game = result.getGame();

    System.out.println("Failure Reason: " + result.getMoveFailureReason());

    return result;
  }

  public Game getGame() {
    return game;
  }

  public GameMode getGameMode() {
    return gameMode;
  }
}
