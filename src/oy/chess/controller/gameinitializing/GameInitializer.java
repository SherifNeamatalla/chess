package oy.chess.controller.gameinitializing;

import oy.chess.model.game.Game;
import oy.chess.model.game.GameMode;
import oy.chess.model.player.Player;
import oy.chess.model.player.PlayerColor;

public class GameInitializer {

  public static Game getInitializedGame(GameMode gameMode) {

    //TODO
    switch (gameMode) {
      case HUMAN_VS_HUMAN:
      case GAME_REPLAY:
        Player whitePlayer = NormalPlayerInitializer.getInitializedPlayer(PlayerColor.WHITE);
        Player blackPlayer = NormalPlayerInitializer.getInitializedPlayer(PlayerColor.BLACK);

        return new Game(whitePlayer, blackPlayer, PlayerColor.WHITE);

      case HUMAN_VS_AI:
        break;
    }
    return null;
  }
}
