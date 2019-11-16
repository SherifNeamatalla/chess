package oy.chess.view.programwindow;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.view.ProgramWindow;

public class ProgramWindowFactory {

  public static void addToolbar(ProgramWindow programWindow) {

    programWindow.setTop(getTopBar());
  }

  public static void addAGNAnnotation(
      ProgramWindow programWindow, ListView<Move> whiteMoves, ListView<Move> blackMoves) {
    programWindow.setRight(getRightPart(whiteMoves, blackMoves));
  }

  public static void addGameList(
      ProgramWindow programWindow, ListView<GameMetaInformation> uploadedGames) {
    programWindow.setLeft(getLeftPart(uploadedGames));
  }

  public static void addReplayOptions(ProgramWindow programWindow) {
    if (ProgramWindow.getGameMode() == GameMode.GAME_REPLAY
        || ProgramWindow.getGameMode() == GameMode.AI_VS_AI)
      programWindow.setBottom(getReplayOptions());
  }

  private static HBox getTopBar() {

    var topBar = new HBox();
    var undo = new Button("Undo");
    var restart = new Button("Restart");
    var save = new Button("Save Annotation");
    var upload = new Button("Upload Game");
    var choose = new Button("Choose Game");

    ProgramWindowLogicHandler.setToolBarLogic(undo, restart, save, upload, choose);

    topBar.getChildren().addAll(undo, restart, save, upload, choose);

    return topBar;
  }

  private static HBox getLeftPart(ListView<GameMetaInformation> uploadedGames) {

    var leftHBox = new HBox();

    ProgramWindowLogicHandler.setUploadedGamesLogic(uploadedGames);

    leftHBox.getChildren().add(uploadedGames);

    return leftHBox;
  }

  private static HBox getReplayOptions() {

    var replayBox = new HBox();
    var nextButton = new Button("Next");
    var backButton = new Button("Back");

    ProgramWindowLogicHandler.setGameReplayOptionsLogic(nextButton, backButton);

    replayBox.getChildren().add(nextButton);
    replayBox.getChildren().add(backButton);

    return replayBox;
  }

  private static HBox getRightPart(ListView<Move> whiteMoves, ListView<Move> blackMoves) {

    var moves = new HBox();
    moves.getChildren().add(blackMoves);
    moves.getChildren().add(whiteMoves);

    ProgramWindowLogicHandler.setMoveHistoryLogic(whiteMoves, blackMoves);

    return moves;
  }
}
