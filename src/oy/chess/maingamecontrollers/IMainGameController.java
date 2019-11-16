package oy.chess.maingamecontrollers;

import javafx.stage.Stage;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.view.model.BoardCell;

import java.io.File;

public interface IMainGameController {
  void startGame(Stage primaryStage);

  Game getGame();

  void doMove(BoardCell boardCell);

  Game startNewGame();

  void uploadGame(GameMetaInformation gameMetaInformation);
}
