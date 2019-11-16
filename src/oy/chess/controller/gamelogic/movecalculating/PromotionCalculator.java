package oy.chess.controller.gamelogic.movecalculating;

import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameStatus;
import oy.chess.model.move.Move;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;

import java.util.Optional;

class PromotionCalculator {
  static boolean isPromotion(Move move, Piece chosenPiece, Game game) {
    if (chosenPiece.getPieceType() != PieceType.PAWN) return false;

    // Movement validation will not be done here, it is assumed that the move is already correct.

    // Checks if it's last row.
    int lastX = game.getCurrentPlayerColor() == PlayerColor.WHITE ? 0 : 7;

    return move.getNewPosition().getX() == lastX;
  }

  static Game calculate(Move move, Game game) {

    Game newGame = GameUtilHelper.copy(game);

    Optional<Piece> newChosenPieceOptional = PieceFinder.findPiece(move.getOldPosition(), newGame);

    if (newChosenPieceOptional.isEmpty()) return newGame;

    Piece newChosenPiece = newChosenPieceOptional.get();

    newChosenPiece.setPosition(move.getNewPosition());

    newChosenPiece.setPieceType(game.getPromotionResult().getPieceType());

    // Resets promotion status
    newGame.setPromotionResult(null);
    newGame.setGameStatus(GameStatus.NORMAL);

    return newGame;
  }
}
