package oy.chess.model.game;

import oy.chess.model.move.Move;

import java.util.List;

public class GameMetaInformation {

  private String event;

  private String site;

  private String date;

  private String round;

  private String whitePlayer;

  private String blackPlayer;

  private String blackElo;

  private String whiteElo;

  private String gameText;

  private List<Move> gameMoves;

  public GameMetaInformation() {}

  public GameMetaInformation(
      String event,
      String site,
      String date,
      String round,
      String whitePlayer,
      String blackPlayer,
      String blackElo,
      String whiteElo,
      String gameText,
      List<Move> gameMoves) {
    this.event = event;
    this.site = site;
    this.date = date;
    this.round = round;
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    this.blackElo = blackElo;
    this.whiteElo = whiteElo;
    this.gameText = gameText;
    this.gameMoves = gameMoves;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getRound() {
    return round;
  }

  public void setRound(String round) {
    this.round = round;
  }

  public String getWhitePlayer() {
    return whitePlayer;
  }

  public void setWhitePlayer(String whitePlayer) {
    this.whitePlayer = whitePlayer;
  }

  public String getBlackPlayer() {
    return blackPlayer;
  }

  public void setBlackPlayer(String blackPlayer) {
    this.blackPlayer = blackPlayer;
  }

  public String getBlackElo() {
    return blackElo;
  }

  public void setBlackElo(String blackElo) {
    this.blackElo = blackElo;
  }

  public String getWhiteElo() {
    return whiteElo;
  }

  public void setWhiteElo(String whiteElo) {
    this.whiteElo = whiteElo;
  }

  public String getGameText() {
    return gameText;
  }

  public void setGameText(String gameText) {
    this.gameText = gameText;
  }

  public List<Move> getGameMoves() {
    return gameMoves;
  }

  public void setGameMoves(List<Move> gameMoves) {
    this.gameMoves = gameMoves;
  }
}
