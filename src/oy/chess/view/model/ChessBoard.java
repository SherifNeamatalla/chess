package oy.chess.view.model;

import javafx.scene.layout.GridPane;
import oy.chess.model.position.Position;

public class ChessBoard extends GridPane {

  private BoardCell[][] boardCells = new BoardCell[8][8];

  private ChessBoardState currentState = ChessBoardState.TURN_START;

  private Position chosenPosition = null;

  public ChessBoard(BoardCell[][] boardCells) {
    super();
    this.setBoardCells(boardCells);
  }

  public BoardCell[][] getBoardCells() {
    return boardCells;
  }

  public void setBoardCells(BoardCell[][] boardCells) {
    this.boardCells = boardCells;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        this.add(boardCells[i][j], j, i);
      }
    }
  }

  public ChessBoardState getCurrentState() {
    return currentState;
  }

  public Position getChosenPosition() {
    return chosenPosition;
  }

  public BoardCell getChosenCell() {
    return this.boardCells[chosenPosition.getX()][chosenPosition.getY()];
  }

  public void setChosenPosition(Position chosenPosition) {
    this.chosenPosition = chosenPosition;
  }

  public void setTurnStartState() {
    this.currentState = ChessBoardState.TURN_START;
    this.chosenPosition = null;
  }

  public void setPieceChosenState(Position position) {
    this.currentState = ChessBoardState.PIECE_CHOSEN;
    this.chosenPosition = position;
  }
}
