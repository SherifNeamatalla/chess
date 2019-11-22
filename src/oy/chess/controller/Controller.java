package oy.chess.controller;

import oy.chess.controller.gameinitializing.GameInitializer;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;

public class Controller {

  public Controller() {}

  public Game startNewGame() {

    return GameInitializer.getInitializedGame();
  }

  public MoveResult doMove(Move move, Game game) {

    MoveResult result = MoveMaker.doGetMoveResult(move, game);

    System.out.println("Failure Reason: " + result.getMoveFailureReason());

    return result;
  }
}
