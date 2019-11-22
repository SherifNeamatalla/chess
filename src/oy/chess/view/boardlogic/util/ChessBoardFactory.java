package oy.chess.view.boardlogic.util;

import oy.chess.maingamecontrollers.AbstractMainGameController;
import oy.chess.view.cell.CellColorSetter;
import oy.chess.view.cell.CellLogicInitializer;
import oy.chess.view.cell.CellPiecesSetter;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public class ChessBoardFactory {

  public static ChessBoard createBoard(AbstractMainGameController controller) {

    BoardCell[][] cells = new BoardCell[8][8];

    CellColorSetter.initializeColors(cells);

    CellPiecesSetter.setPieces(cells, controller.getGame());

    ChessBoard chessBoard = new ChessBoard(cells);

    CellLogicInitializer.initializeLogic(cells, controller);

    return chessBoard;
  }

  public static ChessBoard CreateBoardForReplay(AbstractMainGameController controller) {

    // No initialization for cells logic, no need for it.
    BoardCell[][] cells = new BoardCell[8][8];

    CellColorSetter.initializeColors(cells);

    CellPiecesSetter.setPieces(cells, controller.getGame());

    return new ChessBoard(cells);
  }
}
