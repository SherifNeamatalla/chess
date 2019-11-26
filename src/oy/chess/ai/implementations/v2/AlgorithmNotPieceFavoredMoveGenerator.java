package oy.chess.ai.implementations.v2;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.controller.gamelogic.movecalculating.MoveCalculator;
import oy.chess.controller.gamelogic.movecalculating.verifiers.KingCheckVerifier;
import oy.chess.controller.gamelogic.movechecking.moveavailability.MoveAvailabilityChecker;
import oy.chess.controller.gamelogic.movechecking.movevalidators.MoveValidator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmNotPieceFavoredMoveGenerator implements IAlgorithmMoveGenerator {
  @Override
  public List<Move> generateMoves(Game game, int branchingLimit) {
    List<Move> result = new ArrayList<>();

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        for (Piece piece : currentPlayerPieces) {

          Move move = new Move(piece.getPosition(), new Position(i, j));

          if (MoveValidator.moveIsValid(move, piece, game)
              && MoveAvailabilityChecker.moveIsAvailable(move, piece, game)) {
            Game newGame = MoveCalculator.calculateNormalMove(move, game);

            if (KingCheckVerifier.gameIsCheck(newGame)) {
              continue;
            }

            move.setPieceType(piece.getPieceType());
            result.add(move);
          }
          if (result.size() >= branchingLimit) return result;
        }
      }
    }

    Collections.shuffle(result);
    return result;
  }
}
