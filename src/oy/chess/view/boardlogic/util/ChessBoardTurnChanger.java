package oy.chess.view.boardlogic.util;

import oy.chess.model.game.Game;
import oy.chess.model.game.GameStatus;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.ProgramWindow;
import oy.chess.view.model.ChessBoard;

public class ChessBoardTurnChanger {

  public static boolean changeTurnAndRefreshBoardState(ChessBoard chessBoard, Game game) {

    // This is because the turn has to change
    chessBoard.setTurnStartState();

    boolean playAgain = false;
    // No else if between promotion and check because with one move both can occur
    if (game.getGameStatus() == GameStatus.IS_CHECK_TO_CURRENT_PLAYER) {
      {
        ChessBoardRefresher.refreshBoardWithCheck(chessBoard, game);
      }
    } else if (game.getGameStatus() == GameStatus.IS_STALE_MATE) {
      playAgain = ChessBoardRefresher.playAgainStaleMate();
    } else if (game.getGameStatus() == GameStatus.IS_CHECK_MATE) {
      playAgain = ChessBoardRefresher.playAgainCheckMate(game.getCurrentPlayerColor());
    } else {
      ChessBoardRefresher.refreshBoard(chessBoard, game);
    }

    ProgramWindow.setCurrentMoves(game.getMoves());

    return playAgain;
  }

  public static PieceType getPromotionResult(PlayerColor currentPlayerColor) {

    return ChessBoardPromotionPopupController.getPromotionResult(currentPlayerColor);
  }
}
