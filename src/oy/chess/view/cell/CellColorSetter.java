package oy.chess.view.cell;

import oy.chess.model.position.Position;
import oy.chess.view.model.BoardCell;

public class CellColorSetter {
  public static void initializeColors(BoardCell[][] cells) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
          cells[i][j] = new BoardCell(true, new Position(i, j));
        else {
          cells[i][j] = new BoardCell(false, new Position(i, j));
        }
      }
    }
  }
}
