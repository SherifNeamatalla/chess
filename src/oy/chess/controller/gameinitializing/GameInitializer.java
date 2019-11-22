package oy.chess.controller.gameinitializing;

import oy.chess.model.game.Game;
import oy.chess.model.player.Player;
import oy.chess.model.player.PlayerColor;

public class GameInitializer {

  public static Game getInitializedGame() {

    Player whitePlayer = NormalPlayerInitializer.getInitializedPlayer(PlayerColor.WHITE);
    Player blackPlayer = NormalPlayerInitializer.getInitializedPlayer(PlayerColor.BLACK);

    return new Game(whitePlayer, blackPlayer, PlayerColor.WHITE);
  }
}
