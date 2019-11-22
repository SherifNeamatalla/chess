package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.controller.gamelogic.movechecking.helper.CastlingChecker;
import oy.chess.controller.gamelogic.movechecking.helper.EnpassantChecker;
import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveType;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.util.GameUtilHelper;

import java.util.Optional;

class MoveCalculatorsManager {

  static Game doMove(Move move, Game oldGame) {

    Game newGame = GameUtilHelper.copy(oldGame);

    Optional<Piece> chosenPieceOptional = PieceFinder.findPiece(move.getOldPosition(), newGame);

    if (chosenPieceOptional.isEmpty()) return oldGame;

    var chosenPiece = chosenPieceOptional.get();

    // Special cases for pawn and king, otherwise just change new position and capture old piece if
    // there was any captured pieces.

    if (chosenPiece.getPieceType() == PieceType.PAWN) {

      boolean isEnPassant = EnpassantChecker.isEnpassant(move, newGame);
      if (isEnPassant) {
        newGame = EnpassantCalculator.calculate(move, newGame);
      } else {

        // In case of promotion no calculation is to be done, we have to wait for the user to enter
        // the new piece type.
        boolean isPromotion = PromotionCalculator.isPromotion(move, chosenPiece, newGame);
        if (isPromotion) {
          if (newGame.getPromotionResult() != null) {
            newGame.getCurrentTurnActions().add(MoveType.PROMOTION);
            newGame = PromotionCalculator.calculate(move, newGame);
            newGame = PieceCaptureCalculator.captureIfCaptured(move, newGame);
          }
        } else {
          chosenPiece.setPosition(move.getNewPosition());
          newGame = PieceCaptureCalculator.captureIfCaptured(move, newGame);
        }
      }

    } else if (chosenPiece.getPieceType() == PieceType.KING) {

      boolean isCastling = CastlingChecker.isCastling(move, chosenPiece, newGame);
      if (isCastling) {
        newGame = CastlingCalculator.calculateCastlingForRook(move, chosenPiece, newGame);
        // King or Queen side castling
        if (move.getNewPosition().getY() == 6)
          newGame.getCurrentTurnActions().add(MoveType.KING_SIDE_CASTLING);
        else newGame.getCurrentTurnActions().add(MoveType.QUEEN_SIDE_CASTLING);
      } else {
        chosenPiece.setPosition(move.getNewPosition());
        newGame = PieceCaptureCalculator.captureIfCaptured(move, newGame);
      }
    } else {
      chosenPiece.setPosition(move.getNewPosition());
      newGame = PieceCaptureCalculator.captureIfCaptured(move, newGame);
    }

    return newGame;
  }
}
