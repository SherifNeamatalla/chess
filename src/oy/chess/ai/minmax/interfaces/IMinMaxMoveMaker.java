package oy.chess.ai.minmax.interfaces;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

public interface IMinMaxMoveMaker {

  Game doMove(Move move,Game game);
}
