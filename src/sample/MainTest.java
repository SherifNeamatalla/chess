package sample;

import oy.chess.controller.gameinitializing.GameInitializer;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMode;

public class MainTest {

  public static void main(String[] args) {

    Game game = GameInitializer.getInitializedGame(GameMode.AI_VS_AI);
  }
}
