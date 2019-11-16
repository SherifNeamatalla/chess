package oy.chess.view.boardlogic.util;

import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameStatus;
import oy.chess.view.ProgramWindow;
import oy.chess.view.model.ChessBoard;

public class ChessBoardTurnChanger {

  public static void changeTurnAndRefreshBoardState(
      ChessBoard chessBoard, Game game, Controller controller) {

    // This is because the turn has to change
    chessBoard.setTurnStartState();

    // No else if between promotion and check because with one move both can occur
    if (game.getGameStatus() == GameStatus.IS_CHECK_TO_CURRENT_PLAYER) {
      {
        ChessBoardRefresher.refreshBoardWithCheck(chessBoard, game);
      }
    } else if (game.getGameStatus() == GameStatus.IS_STALE_MATE) {
      game = ChessBoardRefresher.playAgainStaleMate(chessBoard, game, controller);
    } else if (game.getGameStatus() == GameStatus.IS_CHECK_MATE) {
      game = ChessBoardRefresher.playAgainCheckMate(chessBoard, game, controller);
    } else {
      ChessBoardRefresher.refreshBoard(chessBoard, game);
    }

    ProgramWindow.setCurrentMoves(game.getMoves());
  }
}
