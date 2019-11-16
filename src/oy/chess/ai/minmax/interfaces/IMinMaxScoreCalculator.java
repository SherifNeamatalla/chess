package oy.chess.ai.minmax.interfaces;

import oy.chess.model.game.Game;
import oy.chess.model.player.PlayerColor;

public interface IMinMaxScoreCalculator {

  double getScore(Game game, PlayerColor currentPlayer);
}
