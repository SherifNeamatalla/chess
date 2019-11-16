package oy.chess.annotation.model;

public enum AGNColumn {
  A("a"),
  B("b"),
  C("c"),
  D("d"),
  E("e"),
  F("f"),
  G("g"),
  H("h");

  private String value;

  AGNColumn(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
