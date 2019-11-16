package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import oy.chess.maingamecontrollers.IMainGameController;
import oy.chess.maingamecontrollers.MainGameControllerFactory;
import oy.chess.model.game.GameMode;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    IMainGameController controller =
        MainGameControllerFactory.getMainGameControllerInstance(GameMode.AI_VS_AI);
    assert controller != null;
    controller.startGame(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
