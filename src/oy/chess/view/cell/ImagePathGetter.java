package oy.chess.view.cell;

import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;

import java.nio.file.Paths;

public class ImagePathGetter {
  public static String getImage(PieceType pieceType, PlayerColor playerColor) {

    String filename =
        playerColor.toString().toLowerCase() + "_" + pieceType.toString().toLowerCase() + ".png";

    String path =
        Paths.get(".").toAbsolutePath().normalize().toString()
            + "/src/oy/chess/assets/pieces/"
            + filename;

    return "file://" + path;
  }
}
