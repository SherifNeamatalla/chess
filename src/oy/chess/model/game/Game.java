package oy.chess.model.game;

import oy.chess.model.move.Move;
import oy.chess.model.move.MoveType;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.Player;
import oy.chess.model.player.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private Player whitePlayer;

  private Player blackPlayer;

  private PlayerColor currentPlayerColor;

  private GameStatus gameStatus = GameStatus.NORMAL;

  private Piece promotionResult = null;

  private List<Move> moves = new ArrayList<>();

  private List<MoveType> currentTurnActions = new ArrayList<>();

  public Game(Player whitePlayer, Player blackPlayer, PlayerColor currentPlayerColor) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    this.currentPlayerColor = currentPlayerColor;
  }

  public Game(
      Player whitePlayer,
      Player blackPlayer,
      PlayerColor currentPlayerColor,
      GameStatus gameStatus,
      Piece promotionResult,
      List<Move> moves,
      List<MoveType> currentTurnActions) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    this.currentPlayerColor = currentPlayerColor;
    this.gameStatus = gameStatus;
    this.promotionResult = promotionResult;
    this.moves = moves;
    this.currentTurnActions = currentTurnActions;
  }

  public Player getWhitePlayer() {
    return whitePlayer;
  }

  public void setWhitePlayer(Player whitePlayer) {
    this.whitePlayer = whitePlayer;
  }

  public Player getBlackPlayer() {
    return blackPlayer;
  }

  public void setBlackPlayer(Player blackPlayer) {
    this.blackPlayer = blackPlayer;
  }

  public PlayerColor getCurrentPlayerColor() {
    return currentPlayerColor;
  }

  public void setCurrentPlayerColor(PlayerColor currentPlayerColor) {
    this.currentPlayerColor = currentPlayerColor;
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(GameStatus gamestatus) {
    this.gameStatus = gamestatus;
  }

  public Piece getPromotionResult() {
    return promotionResult;
  }

  public void setPromotionResult(Piece promotionResult) {
    this.promotionResult = promotionResult;
  }

  public List<Move> getMoves() {
    return moves;
  }

  public void setMoves(List<Move> moves) {
    this.moves = moves;
  }

  public List<MoveType> getCurrentTurnActions() {
    return currentTurnActions;
  }

  public void setCurrentTurnActions(List<MoveType> currentTurnActions) {
    this.currentTurnActions = currentTurnActions;
  }
}
