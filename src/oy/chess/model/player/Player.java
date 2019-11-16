package oy.chess.model.player;

import oy.chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

  private PlayerColor playerColor;

  private List<Piece> pieces;

  public Player(PlayerColor playerColor, List<Piece> pieces) {
    this.playerColor = playerColor;
    this.pieces = pieces;
  }



  public PlayerColor getPlayerColor() {
    return playerColor;
  }

  public void setPlayerColor(PlayerColor playerColor) {
    this.playerColor = playerColor;
  }

  public List<Piece> getPieces() {
    return pieces;
  }

  public void setPieces(List<Piece> pieces) {
    this.pieces = pieces;
  }
}
