package oy.chess.ai.minmax.interfaces;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

import java.util.List;

public interface IMinMaxMoveGenerator {
  List<Move> generateMoves(Game game, int branchingLimit);
}
