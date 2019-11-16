package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardLogicCommonFunctions;
import oy.chess.view.boardlogic.ChessBoardTwoPlayersModeLogicHandler;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;
import oy.chess.view.model.ChessBoardState;

public class TwoPlayersMainGameController implements IMainGameController {

  private Controller controller;

  private ChessBoard chessBoard;

  public void startGame(Stage primaryStage) {

    controller = new Controller(GameMode.HUMAN_VS_HUMAN);
    chessBoard = ChessBoardFactory.createBoard(this);
    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.HUMAN_VS_HUMAN);
    primaryStage.setTitle("Plumbes Chess PvP Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public Game getGame() {
    return controller.getGame();
  }

  @Override
  public void doMove(BoardCell boardCell) {

    if (chessBoard.getCurrentState() == ChessBoardState.TURN_START
        && boardCell.getPiece() != null) {

      ChessBoardLogicCommonFunctions.setPieceChosenState(chessBoard, boardCell);

    } else if (chessBoard.getCurrentState() == ChessBoardState.PIECE_CHOSEN) {

      ChessBoardTwoPlayersModeLogicHandler.performMove(chessBoard, boardCell, controller);
    }
  }

  @Override
  public Game startNewGame() {
    return controller.startNewGame();
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {
    // TODO after save is implemented.
  }
}
