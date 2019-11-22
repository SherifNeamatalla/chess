package oy.chess.ai.implementations.v1;

import oy.chess.ai.minmax.interfaces.IMinMaxMoveGenerator;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxDumbMoveGenerator implements IMinMaxMoveGenerator {
  @Override
  public List<Move> generateMoves(Game game, int branchingLimit) {

    List<Move> result = new ArrayList<>();

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

    for (Piece piece : currentPlayerPieces) {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          Move move = new Move(piece.getPosition(), new Position(i, j));

          MoveResult moveResult = MoveMaker.doGetMoveResult(move, game);
          if (!moveResult.isSuccess()) continue;
          move.setPieceType(piece.getPieceType());
          result.add(move);
          if (result.size() >= branchingLimit) return result;
        }
      }
    }

    // If not shuffled the result will always be the same
    Collections.shuffle(result);
    return result;
  }
}
