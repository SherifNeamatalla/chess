package oy.chess.annotation.parsing;

import oy.chess.model.game.GameMetaInformation;

import java.io.File;
import java.util.List;

public class AGNFileOpener {

  public static List<GameMetaInformation> openFile(File file) {

    // Can be used in the future for different file formats.
    return FileReader.readFile(file);
  }
}
