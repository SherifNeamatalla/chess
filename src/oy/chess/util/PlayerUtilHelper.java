package oy.chess.util;

import oy.chess.model.piece.Piece;
import oy.chess.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtilHelper {

  public static Player copy(Player oldPlayer) {
    List<Piece> newPieces = new ArrayList<>();
    oldPlayer.getPieces().forEach(p -> newPieces.add(PieceUtilHelper.copy(p)));
    return new Player(oldPlayer.getPlayerColor(), newPieces);
  }
}
