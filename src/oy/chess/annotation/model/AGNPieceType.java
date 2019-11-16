package oy.chess.annotation.model;

public enum AGNPieceType {
  K("K"),
  Q("Q"),
  B("B"),
  N("N"),
  R("R"),
  NONE("");

  private String value;

  AGNPieceType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
