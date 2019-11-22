package oy.chess.view.boardlogic.util;

import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.util.GameUtilHelper;
import oy.chess.view.cell.CellPiecesSetter;
import oy.chess.view.model.ChessBoard;

import java.util.Optional;

public class ChessBoardRefresher {

  public static void refreshBoard(ChessBoard board, Game game) {
    CellPiecesSetter.setPieces(board.getBoardCells(), game);

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        board.getBoardCells()[i][j].setChosen(false);
        board.getBoardCells()[i][j].setColorChecked(false);
      }
    }

    if (board.getChosenPosition() != null)
      board.getBoardCells()[board.getChosenPosition().getX()][board.getChosenPosition().getY()]
          .setChosen(true);

    board.setTurnStartState();
  }

  static boolean playAgainStaleMate() {

    return ChessBoardPromotionPopupController.isPlayAgainStaleMate();
  }

  static boolean playAgainCheckMate(PlayerColor playerColor) {
    return ChessBoardPromotionPopupController.isPlayAgainCheckMate(playerColor);
  }

  static void refreshBoardWithCheck(ChessBoard chessBoard, Game game) {

    refreshBoard(chessBoard, game);

    Optional<Piece> kingOptional =
        GameUtilHelper.getCurrentPlayerPieces(game)
            .parallelStream()
            .filter(p -> p.getPieceType() == PieceType.KING)
            .findFirst();

    if (kingOptional.isPresent()) {
      Piece king = kingOptional.get();
      chessBoard.getBoardCells()[king.getPosition().getX()][king.getPosition().getY()]
          .setColorChecked(true);
    }
  }
}
