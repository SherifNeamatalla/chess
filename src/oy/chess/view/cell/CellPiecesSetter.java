package oy.chess.view.cell;

import oy.chess.model.game.Game;
import oy.chess.view.model.BoardCell;

public class CellPiecesSetter {

  public static void setPieces(BoardCell[][] cells, Game game) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        cells[i][j].setPiece(null);
      }
    }
    game.getBlackPlayer()
        .getPieces()
        .forEach(
            piece -> {
              int x = piece.getPosition().getX();
              int y = piece.getPosition().getY();

              cells[x][y].setPiece(piece);
            });

    game.getWhitePlayer()
        .getPieces()
        .forEach(
            piece -> {
              int x = piece.getPosition().getX();
              int y = piece.getPosition().getY();

              cells[x][y].setPiece(piece);
            });
  }
}
