package oy.chess.view.boardlogic.util;

import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public class ChessBoardLogicCommonFunctions {
  public static void setPieceChosenState(ChessBoard chessBoard, BoardCell boardCell) {
    boardCell.setChosen(true);

    chessBoard.setPieceChosenState(boardCell.getPosition());
  }
}
