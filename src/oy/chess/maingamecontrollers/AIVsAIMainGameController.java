package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.ai.AIPlayer;
import oy.chess.ai.algorithm.model.*;
import oy.chess.controller.gamelogic.movecalculating.PromotionCalculator;
import oy.chess.controller.gamelogic.movechecking.helper.PieceFinder;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;

public class AIVsAIMainGameController extends AbstractMainGameController {

  private AIPlayer whitePlayer =
      new AIPlayer(
          new AlgorithmConfiguration(
              300,
              2,
              AlgorithmHeuristic.MATERIAL,
              AlgorithmMoveGeneratingStrategy.BASIC,
              AlgorithmMoveChoosingStrategy.BASIC,
              Algorithm.MINMAX));

  private AIPlayer blackPlayer =
      new AIPlayer(
          new AlgorithmConfiguration(
              300,
              1,
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

    AIPlayer currentPlayer = getCurrentPlayer(getGame().getCurrentPlayerColor());

    assert currentPlayer != null;
    Move move = currentPlayer.getBestMove(getGame());

    if (PromotionCalculator.isPromotion(
        move, PieceFinder.findPiece(move.getOldPosition(), game).get(), game)) {
      game.setPromotionResult(new Piece(1, null, move.getPieceType(), null, true));
    }

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
