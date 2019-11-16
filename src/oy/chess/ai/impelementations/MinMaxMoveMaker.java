package oy.chess.ai.impelementations;

import oy.chess.ai.minmax.interfaces.IMinMaxMoveMaker;
import oy.chess.controller.gamelogic.movecalculating.MoveCalculator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.util.GameUtilHelper;

public class MinMaxMoveMaker implements IMinMaxMoveMaker {
  @Override
  public Game doMove(Move move, Game game) {

    Game newGame = MoveCalculator.calculateNormalMove(move, game);
    newGame = GameUtilHelper.changeTurnTemporarily(newGame);
    return newGame;
  }
}
