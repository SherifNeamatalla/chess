package oy.chess.annotation.model;

import oy.chess.model.move.MoveType;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;

import java.util.List;

public class AGNMove {

  private AGNPosition oldPosition;

  private AGNPosition newPosition;

  private int moveNumber;

  private PlayerColor playerColor;

  private PieceType pieceType;

  private List<MoveType> moveTypes;

  public AGNMove(
      AGNPosition oldPosition,
      AGNPosition newPosition,
      int moveNumber,
      PlayerColor playerColor,
      PieceType pieceType,
      List<MoveType> moveTypes) {
    this.oldPosition = oldPosition;
    this.newPosition = newPosition;
    this.moveNumber = moveNumber;
    this.playerColor = playerColor;
    this.pieceType = pieceType;
    this.moveTypes = moveTypes;
  }

  public List<MoveType> getMoveTypes() {
    return moveTypes;
  }

  public void setMoveTypes(List<MoveType> moveTypes) {
    this.moveTypes = moveTypes;
  }

  public AGNPosition getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(AGNPosition oldPosition) {
    this.oldPosition = oldPosition;
  }

  public AGNPosition getNewPosition() {
    return newPosition;
  }

  public void setNewPosition(AGNPosition newPosition) {
    this.newPosition = newPosition;
  }

  public int getMoveNumber() {
    return moveNumber;
  }

  public void setMoveNumber(int moveNumber) {
    this.moveNumber = moveNumber;
  }

  public PlayerColor getPlayerColor() {
    return playerColor;
  }

  public void setPlayerColor(PlayerColor playerColor) {
    this.playerColor = playerColor;
  }

  public PieceType getPieceType() {
    return pieceType;
  }

  public void setPieceTypes(PieceType pieceType) {
    this.pieceType = pieceType;
  }
}
