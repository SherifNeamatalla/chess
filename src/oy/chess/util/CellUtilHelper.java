package oy.chess.util;

import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;

import java.util.List;

public class CellUtilHelper {

  public static boolean cellIsAvailable(Position position, List<Piece> pieces) {
    for (Piece p : pieces) {
      if (p.getPosition().equals(position)) return false;
    }
    return true;
  }
}
