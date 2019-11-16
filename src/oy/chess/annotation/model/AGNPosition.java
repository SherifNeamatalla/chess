package oy.chess.annotation.model;

import oy.chess.model.piece.PieceType;

public class AGNPosition {
  private AGNRow row;

  private AGNColumn column;

  private PieceType pieceType;

  public AGNPosition(AGNRow row, AGNColumn column) {
    this.row = row;
    this.column = column;
  }

  public AGNPosition(AGNRow row, AGNColumn column, PieceType pieceType) {
    this.row = row;
    this.column = column;
    this.pieceType = pieceType;
  }

  public PieceType getPieceType() {
    return pieceType;
  }

  public void setPieceType(PieceType pieceType) {
    this.pieceType = pieceType;
  }

  public AGNRow getRow() {
    return row;
  }

  public void setRow(AGNRow row) {
    this.row = row;
  }

  public AGNColumn getColumn() {
    return column;
  }

  public void setColumn(AGNColumn column) {
    this.column = column;
  }
}
