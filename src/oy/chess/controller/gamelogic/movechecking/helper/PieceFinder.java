package oy.chess.controller.gamelogic.movechecking.helper;

import oy.chess.util.GameUtilHelper;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceFinder {
  public static Optional<Piece> findPiece(Position position, Game game) {
    if (position == null || game == null) return Optional.empty();

    List<Piece> playerPieces = new ArrayList<>(GameUtilHelper.getIdlePlayerPieces(game));
    playerPieces.addAll(GameUtilHelper.getCurrentPlayerPieces(game));

    return playerPieces
        .parallelStream()
        .filter(piece -> piece.getPosition().equals(position))
        .findFirst();
  }
}
