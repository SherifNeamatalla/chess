package oy.chess.controller.gameinitializing;

public class PieceSequenceGenerator {

  private static int sequence = 0;

  static int getNextSequence() {
    return sequence++;
  }
}
