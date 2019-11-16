package oy.chess.view.boardlogic;

import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.view.boardlogic.util.ChessBoardRefresher;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public class ChessBoardReplayModeLogicHandler {

  public static void performMove(
      ChessBoard chessBoard, BoardCell boardCell, Controller controller) {

    MoveResult moveResult =
        controller.doMove(new Move(chessBoard.getChosenPosition(), boardCell.getPosition()));

    Game game = moveResult.getGame();

    ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, game, controller);
  }

  public static void performMoveWithPromotion(
      ChessBoard chessBoard,
      BoardCell boardCell,
      Controller controller,
      PieceType promotionResult) {

    controller.doMove(new Move(chessBoard.getChosenPosition(), boardCell.getPosition()));

    controller.getGame().setPromotionResult(new Piece(1, null, promotionResult, null, true));

    MoveResult moveResultAfterPromotion =
        controller.doMove(new Move(chessBoard.getChosenPosition(), boardCell.getPosition()));

    Game newGame = moveResultAfterPromotion.getGame();

    ChessBoardRefresher.refreshBoard(chessBoard, newGame);

    ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, newGame, controller);
  }
}
