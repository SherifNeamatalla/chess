package oy.chess.ai.algorithm.interfaces;

import oy.chess.model.game.Game;
import oy.chess.model.move.Move;

import java.util.List;

public interface IAlgorithmMoveGenerator {
  List<Move> generateMoves(Game game, int branchingLimit);
}
