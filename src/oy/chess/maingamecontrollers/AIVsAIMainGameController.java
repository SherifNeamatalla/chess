package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.ai.AIPlayer;
import oy.chess.ai.algorithm.model.*;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;

public class AIVsAIMainGameController extends AbstractMainGameController {

  private AIPlayer whitePlayer =
      new AIPlayer(
          new AlgorithmConfiguration(
              100,
              3,
              AlgorithmHeuristic.MATERIAL,
              AlgorithmMoveGeneratingStrategy.BASIC,
              AlgorithmMoveChoosingStrategy.BASIC,
              Algorithm.ALPHA_BETA));

  private AIPlayer blackPlayer =
      new AIPlayer(
          new AlgorithmConfiguration(
              100,
              3,
              AlgorithmHeuristic.MATERIAL,
              AlgorithmMoveGeneratingStrategy.BASIC,
              AlgorithmMoveChoosingStrategy.BASIC,
              Algorithm.MINMAX));

  private static final long WAITING_TIME_MS = 1000;

  @Override
  public void startGame(Stage primaryStage) {

    super.startGame(primaryStage);
    this.chessBoard = ChessBoardFactory.CreateBoardForReplay(this);

    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.AI_VS_AI);
    primaryStage.setTitle("Plumbes Chess AI Vs AI Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public void doMove(BoardCell boardCell) {

    AIPlayer currentPlayer =
        getCurrentPlayer(getGame().getCurrentPlayerColor());

    assert currentPlayer != null;
    Move move = currentPlayer.getBestMove(getGame());

    chessBoard.setChosenPosition(move.getOldPosition());

    MoveResult moveResult = this.controller.doMove(move, this.game);

    if (moveResult.isSuccess()) {
      this.game = moveResult.getGame();

      ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, this.game);
    }
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {}

  private AIPlayer getCurrentPlayer(PlayerColor playerColor) {
    if (playerColor == PlayerColor.WHITE) return whitePlayer;
    else if (playerColor == PlayerColor.BLACK) return blackPlayer;
    return null;
  }
}
