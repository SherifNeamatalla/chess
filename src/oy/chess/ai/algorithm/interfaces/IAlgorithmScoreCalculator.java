package oy.chess.ai.algorithm.interfaces;

import oy.chess.model.game.Game;
import oy.chess.model.player.PlayerColor;

public interface IAlgorithmScoreCalculator {

  double getScore(Game game, PlayerColor currentPlayer);
}
