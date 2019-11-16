package oy.chess.model.piece;

import oy.chess.model.player.PlayerColor;
import oy.chess.model.position.Position;

public class Piece {

  private Position position;

  private PieceType pieceType;

  private final PlayerColor ownerPlayerColor;

  private boolean hasMoved;

  private final int pieceID;

  private int numberOfMoves = 0;

  private int numberOfTurnsSinceFirstMove = 0;


  public Piece(int pieceID, Position position, PieceType pieceType, PlayerColor ownerPlayerColor, boolean hasMoved, int numberOfMoves, int numberOfTurnsSinceFirstMove) {
    this.position = position;
    this.pieceType = pieceType;
    this.ownerPlayerColor = ownerPlayerColor;
    this.pieceID = pieceID;
    this.hasMoved = hasMoved;
    this.numberOfMoves = numberOfMoves;
    this.numberOfTurnsSinceFirstMove = numberOfTurnsSinceFirstMove;
  }

  public Piece(int pieceID, Position position, PieceType pieceType, PlayerColor ownerPlayerColor, boolean hasMoved) {
    this.position = position;
    this.pieceType = pieceType;
    this.ownerPlayerColor = ownerPlayerColor;
    this.hasMoved = hasMoved;
    this.pieceID = pieceID;
  }

  public PieceType getPieceType() {
    return pieceType;
  }

  public void setPieceType(PieceType pieceType) {
    this.pieceType = pieceType;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
    this.hasMoved = true;
    this.numberOfMoves++;
  }

  public int getNumberOfMoves() {
    return numberOfMoves;
  }

  public boolean hasMoved() {
    return hasMoved;
  }

  public PlayerColor getOwnerPlayerColor() {
    return ownerPlayerColor;
  }

  public int getPieceID() {
    return pieceID;
  }

  public int getNumberOfTurnsSinceFirstMove() {
    return numberOfTurnsSinceFirstMove;
  }

  public void increaseNumberOfTurnsSinceFirstMove() {
    this.numberOfTurnsSinceFirstMove++;
  }
}
