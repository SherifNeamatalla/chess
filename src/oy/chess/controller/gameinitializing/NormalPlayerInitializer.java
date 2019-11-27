package oy.chess.controller.gameinitializing;

import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.HumanPlayer;
import oy.chess.model.player.Player;
import oy.chess.model.player.PlayerColor;
import oy.chess.model.position.Position;

import java.util.ArrayList;
import java.util.List;

class NormalPlayerInitializer {

  static Player getInitializedPlayer(PlayerColor playerColor) {

    int x = 7;
    int x_1 = 6;
    if (playerColor == PlayerColor.BLACK) {
      x = 0;
      x_1 = 1;
    }
    List<Piece> pieces = new ArrayList<>();

    // King
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 4),
            PieceType.KING,
            playerColor,
            false));
    if (playerColor == PlayerColor.BLACK) return new HumanPlayer(playerColor, pieces);
/*
    // Queen
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 3),
            PieceType.QUEEN,
            playerColor,
            false));
    // Bishops
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 2),
            PieceType.BISHOP,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 5),
            PieceType.BISHOP,
            playerColor,
            false));
    // Knights
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 1),
            PieceType.KNIGHT,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 6),
            PieceType.KNIGHT,
            playerColor,
            false));
    // Rooks
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 0),
            PieceType.ROOK,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x, 7),
            PieceType.ROOK,
            playerColor,
            false));


 */
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 0),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 1),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 2),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 3),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 4),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 5),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 6),
            PieceType.PAWN,
            playerColor,
            false));
    pieces.add(
        new Piece(
            PieceSequenceGenerator.getNextSequence(),
            new Position(x_1, 7),
            PieceType.PAWN,
            playerColor,
            false));

    return new HumanPlayer(playerColor, pieces);
  }
}
