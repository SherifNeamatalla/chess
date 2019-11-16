package oy.chess.view.programwindow;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import oy.chess.annotation.transforming.AnnotationHandler;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.move.Move;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardRefresher;
import oy.chess.view.model.BrowsePopup;

import java.io.File;

class ProgramWindowLogicHandler {

  static void setToolBarLogic(
      Button undo, Button restart, Button save, Button upload, Button choose) {

    undo.setOnAction(actionEvent -> {});

    upload.setOnAction(
        actionEvent -> {
          final FileChooser fileChooser = new FileChooser();
          final BrowsePopup browsePopup = new BrowsePopup();
          final File file = fileChooser.showOpenDialog(browsePopup);
          if (file != null) ProgramWindow.setUploadedFiles(file);
        });

    choose.setOnAction(
        actionEvent -> {
          GameMetaInformation gameMetaInformation =
              ProgramWindow.getUploadedGames().getSelectionModel().getSelectedItem();

          if (gameMetaInformation != null) {

            ProgramWindow.getController().uploadGame(gameMetaInformation);
            ProgramWindow.getWhiteMoves().getItems().clear();
            ProgramWindow.getBlackMoves().getItems().clear();
          }
        });

    restart.setOnAction(
        actionEvent -> {
          ProgramWindow.getController().startNewGame();
          ChessBoardRefresher.refreshBoard(
              ProgramWindow.getChessBoard(), ProgramWindow.getController().getGame());
          ProgramWindow.getWhiteMoves().getItems().clear();
          ProgramWindow.getBlackMoves().getItems().clear();
        });

    save.setOnAction(actionEvent -> {});
  }

  static void setUploadedGamesLogic(ListView<GameMetaInformation> uploadedGames) {
    uploadedGames.setCellFactory(
        c ->
            new ListCell<>() {
              @Override
              public void updateItem(GameMetaInformation item, boolean x) {
                super.updateItem(item, x);
                if (x) setText(" ");
                else {
                  setText("Round : " + item.getRound());
                }
              }
            });
  }

  static void setGameReplayOptionsLogic(Button nextButton, Button backButton) {
    nextButton.setOnAction(actionEvent -> ProgramWindow.getController().doMove(null));
    backButton.setOnAction(actionEvent -> {});
  }

  static void setMoveHistoryLogic(ListView<Move> whiteMoves, ListView<Move> blackMoves) {
    whiteMoves.setCellFactory(
        c ->
            new ListCell<>() {
              @Override
              public void updateItem(Move item, boolean x) {
                super.updateItem(item, x);
                if (x) setText(" ");
                else {
                  setText(AnnotationHandler.getAnnotationFromMove(item,ProgramWindow.getController().getGame()));
                }
              }
            });

    blackMoves.setCellFactory(
        c ->
            new ListCell<>() {
              @Override
              public void updateItem(Move item, boolean x) {
                super.updateItem(item, x);
                if (x) setText(" ");
                else {
                  setText(AnnotationHandler.getAnnotationFromMove(item,ProgramWindow.getController().getGame()));
                }
              }
            });
  }
}
