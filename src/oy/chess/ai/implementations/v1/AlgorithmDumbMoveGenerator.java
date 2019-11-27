package oy.chess.ai.implementations.v1;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveGenerator;
import oy.chess.controller.gamelogic.MoveMaker;
import oy.chess.controller.gamelogic.movecalculating.PromotionCalculator;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveFailureReason;
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.position.Position;
import oy.chess.util.GameUtilHelper;
import oy.chess.util.MoveUtilHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmDumbMoveGenerator implements IAlgorithmMoveGenerator {
  @Override
  public List<Move> generateMoves(Game game, int branchingLimit) {

    List<Move> result = new ArrayList<>();

    List<Piece> currentPlayerPieces = GameUtilHelper.getCurrentPlayerPieces(game);

      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          for (Piece piece : currentPlayerPieces) {

            Move move = new Move(piece.getPosition(), new Position(i, j));

          MoveResult moveResult = MoveMaker.doGetMoveResult(move, game);
          if (moveResult.isSuccess()) {

            move.setPieceType(piece.getPieceType());
            result.add(move);

          } else if (moveResult.getMoveFailureReason() == MoveFailureReason.NO_PROMOTION_TARGET) {

            Game bishopPromotionGame = GameUtilHelper.copy(game);
            bishopPromotionGame.setPromotionResult(
                new Piece(1, null, PieceType.BISHOP, null, true));

            Game knightPromotionGame = GameUtilHelper.copy(game);
            knightPromotionGame.setPromotionResult(
                new Piece(1, null, PieceType.KNIGHT, null, true));

            Game queenPromotionGame = GameUtilHelper.copy(game);
            queenPromotionGame.setPromotionResult(new Piece(1, null, PieceType.QUEEN, null, true));

            Game rookPromotionGame = GameUtilHelper.copy(game);
            rookPromotionGame.setPromotionResult(new Piece(1, null, PieceType.ROOK, null, true));

            List<Game> toBeObservedGames =
                List.of(
                    bishopPromotionGame,
                    knightPromotionGame,
                    queenPromotionGame,
                    rookPromotionGame);

            for (Game observedGame : toBeObservedGames) {
              MoveResult promotionMoveResult = MoveMaker.doGetMoveResult(move, observedGame);
              Move newMove = MoveUtilHelper.copy(move);
              if (!promotionMoveResult.isSuccess()) continue;
              // Will be used later as a reference
              newMove.setPieceType(observedGame.getPromotionResult().getPieceType());
              result.add(newMove);
            }
          }

          if (result.size() >= branchingLimit) return result;
        }
      }
    }

    // If not shuffled the result will always be the same
    Collections.shuffle(result);
    return result;
  }
}
