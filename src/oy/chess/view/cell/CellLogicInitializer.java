package oy.chess.view.cell;

import oy.chess.maingamecontrollers.AbstractMainGameController;
import oy.chess.view.model.BoardCell;

public class CellLogicInitializer {

  public static void initializeLogic(BoardCell[][] cells, AbstractMainGameController controller) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j] = initializeCellLogic(cells[i][j], controller);
      }
    }
  }

  private static BoardCell initializeCellLogic(
      BoardCell boardCell, AbstractMainGameController controller) {
    boardCell.setOnAction(
        actionEvent -> {
          controller.doMove(boardCell);
        });

    return boardCell;
  };
}
