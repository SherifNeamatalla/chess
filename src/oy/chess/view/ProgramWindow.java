package oy.chess.view;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import oy.chess.annotation.parsing.AGNFileOpener;
import oy.chess.maingamecontrollers.IMainGameController;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.model.ChessBoard;
import oy.chess.view.programwindow.ProgramWindowFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramWindow extends BorderPane {

  // Singelton
  private static ProgramWindow programWindow;

  private static IMainGameController controller;

  private static ChessBoard chessBoard;

  private static ListView<Move> whiteMoves, blackMoves;

  private static ListView<GameMetaInformation> uploadedGames;

  private ProgramWindow(ChessBoard chessBoard, IMainGameController controller, GameMode gameMode) {

    ProgramWindow.chessBoard = chessBoard;
    ProgramWindow.controller = controller;

    whiteMoves = new ListView<>();
    blackMoves = new ListView<>();
    uploadedGames = new ListView<>();

    this.setCenter(chessBoard);

    ProgramWindowFactory.addToolbar(this);
    ProgramWindowFactory.addAGNAnnotation(this, whiteMoves, blackMoves);
    ProgramWindowFactory.addGameList(this, uploadedGames);

    if (gameMode == GameMode.GAME_REPLAY) ProgramWindowFactory.addReplayOptions(this);
  }

  public static ProgramWindow getInstance(
      ChessBoard chessBoard, IMainGameController controller, GameMode gameMode) {

    if (ProgramWindow.programWindow == null) {
      ProgramWindow.programWindow = new ProgramWindow(chessBoard, controller, gameMode);
      return programWindow;
    }

    return ProgramWindow.programWindow;
  }

  public static void setUploadedFiles(File file) {

    List<GameMetaInformation> parsedGames = AGNFileOpener.openFile(file);

    ProgramWindow.uploadedGames.getItems().clear();

    ProgramWindow.uploadedGames.getItems().addAll(parsedGames);

    ProgramWindow.uploadedGames.scrollTo(ProgramWindow.uploadedGames.getItems().size() - 1);
  }

  public static void setCurrentMoves(List<Move> moves) {

    List<Move> whiteMovesOnly =
        moves
            .parallelStream()
            .filter(m -> m.getPlayerColor() == PlayerColor.WHITE)
            .collect(Collectors.toList());
    List<Move> blackMovesOnly =
        moves
            .parallelStream()
            .filter(m -> m.getPlayerColor() == PlayerColor.BLACK)
            .collect(Collectors.toList());

    ProgramWindow.whiteMoves.getItems().clear();
    ProgramWindow.whiteMoves.getItems().addAll(whiteMovesOnly);

    ProgramWindow.blackMoves.getItems().clear();
    ProgramWindow.blackMoves.getItems().addAll(blackMovesOnly);

    ProgramWindow.blackMoves.scrollTo(ProgramWindow.blackMoves.getItems().size() - 1);
    ProgramWindow.whiteMoves.scrollTo(ProgramWindow.whiteMoves.getItems().size() - 1);
  }

  public static ListView<Move> getWhiteMoves() {
    return whiteMoves;
  }

  public static void setWhiteMoves(ListView<Move> whiteMoves) {
    ProgramWindow.whiteMoves = whiteMoves;
  }

  public static ListView<Move> getBlackMoves() {
    return blackMoves;
  }

  public static void setBlackMoves(ListView<Move> blackMoves) {
    ProgramWindow.blackMoves = blackMoves;
  }

  public static ListView<GameMetaInformation> getUploadedGames() {
    return uploadedGames;
  }

  public static void setUploadedGames(ListView<GameMetaInformation> uploadedGames) {
    ProgramWindow.uploadedGames = uploadedGames;
  }

  public static IMainGameController getController() {
    return controller;
  }

  public static void setController(IMainGameController controller) {
    ProgramWindow.controller = controller;
  }

  public static ChessBoard getChessBoard() {
    return chessBoard;
  }

  public static void setChessBoard(ChessBoard chessBoard) {
    ProgramWindow.chessBoard = chessBoard;
  }
}
