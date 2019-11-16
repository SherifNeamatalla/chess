package oy.chess.annotation.model;

public enum AGNRow {
  _1("1"),
  _2("2"),
  _3("3"),
  _4("4"),
  _5("5"),
  _6("6"),
  _7("7"),
  _8("8");

  private String value;

  AGNRow(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
