package oy.chess.maingamecontrollers;

import oy.chess.model.game.GameMode;

public class MainGameControllerFactory {

  public static IMainGameController getMainGameControllerInstance(GameMode gameMode) {
    switch (gameMode) {
      case HUMAN_VS_HUMAN:
        return new TwoPlayersMainGameController();
      case GAME_REPLAY:
        return new ReplayMainGameController();
      case AI_VS_AI:
        return new AIVsAIMainGameController();
    }
    return null;
  }
}
