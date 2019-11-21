package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.ai.minmax.MinMaxRunner;
import oy.chess.ai.minmax.model.MinMaxHeuristic;
import oy.chess.ai.minmax.model.MinMaxPieceChoosingStrategy;
import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.ChessBoardReplayModeLogicHandler;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

public class AIVsAIMainGameController implements IMainGameController {

  private Controller controller;

  private ChessBoard chessBoard;

  private MinMaxRunner minMaxRunnerWhitePlayer =
      new MinMaxRunner(MinMaxHeuristic.MATERIAL, MinMaxPieceChoosingStrategy.NOT_PIECE_FAVORED);

  private MinMaxRunner minMaxRunnerBlackPlayer =
      new MinMaxRunner(MinMaxHeuristic.MATERIAL, MinMaxPieceChoosingStrategy.FIRST_FOUND);

  private static final long WAITING_TIME_MS = 1000;

  @Override
  public void startGame(Stage primaryStage) {

    controller = new Controller(GameMode.AI_VS_AI);
    controller.startNewGame(GameMode.AI_VS_AI);
    chessBoard = ChessBoardFactory.CreateBoardForReplay(this);
    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.AI_VS_AI);
    primaryStage.setTitle("Plumbes Chess AI Vs AI Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public Game getGame() {
    return controller.getGame();
  }

  @Override
  public void doMove(BoardCell boardCell) {

    MinMaxRunner currentRunner = getCurrentMinMaxPlayer(getGame().getCurrentPlayerColor());

    assert currentRunner != null;
    Move move = currentRunner.getBestMove(getGame());

    boardCell =
        chessBoard.getBoardCells()[move.getNewPosition().getX()][move.getNewPosition().getY()];

    chessBoard.setChosenPosition(move.getOldPosition());

    ChessBoardReplayModeLogicHandler.performMove(chessBoard, boardCell, controller);

    doMove(boardCell);
  }

  @Override
  public Game startNewGame() {

    return controller.startNewGame();
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {}

  private MinMaxRunner getCurrentMinMaxPlayer(PlayerColor playerColor) {
    if (playerColor == PlayerColor.WHITE) return minMaxRunnerWhitePlayer;
    else if (playerColor == PlayerColor.BLACK) return minMaxRunnerBlackPlayer;
    return null;
  }
}
