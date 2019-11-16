package oy.chess.model.move;

import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.model.position.Position;

import java.util.List;

public class Move {

  private Position oldPosition;

  private Position newPosition;

  private int moveNumber;

  private PlayerColor playerColor;

  private PieceType pieceType;

  private List<MoveType> moveTypes;

  public Move(Position oldPosition, Position newPosition) {
    this.oldPosition = oldPosition;
    this.newPosition = newPosition;
  }

  public Move(
      Position oldPosition,
      Position newPosition,
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

  public Position getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Position oldPosition) {
    this.oldPosition = oldPosition;
  }

  public Position getNewPosition() {
    return newPosition;
  }

  public void setNewPosition(Position newPosition) {
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

  public void setPieceType(PieceType pieceType) {
    this.pieceType = pieceType;
  }

  public List<MoveType> getMoveTypes() {
    return moveTypes;
  }

  public void setMoveTypes(List<MoveType> moveTypes) {
    this.moveTypes = moveTypes;
  }
}
