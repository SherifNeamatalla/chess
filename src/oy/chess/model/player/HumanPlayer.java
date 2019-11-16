package oy.chess.model.player;

import oy.chess.model.piece.Piece;

import java.util.List;

public class HumanPlayer extends Player {
  public HumanPlayer(PlayerColor playerColor, List<Piece> pieces) {
    super(playerColor, pieces);
  }
}
