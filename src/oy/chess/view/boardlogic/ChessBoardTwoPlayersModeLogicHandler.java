package oy.chess.view.boardlogic;

import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameStatus;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.view.boardlogic.util.ChessBoardRefresher;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public class ChessBoardTwoPlayersModeLogicHandler {

  public static void performMove(
      ChessBoard chessBoard, BoardCell boardCell, Controller controller) {

    MoveResult moveResult =
        controller.doMove(new Move(chessBoard.getChosenPosition(), boardCell.getPosition()));

    Game game = moveResult.getGame();

    if (game.getGameStatus() == GameStatus.IS_WAITING_FOR_PROMOTION_CHOICE) {
      game = ChessBoardRefresher.refreshBoardWithPromotion(chessBoard, game, boardCell, controller);
    }

    ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, game, controller);
  }
}
