package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.annotation.parsing.AGNGameParser;
import oy.chess.annotation.parsing.AGNMoveParser;
import oy.chess.controller.Controller;
import oy.chess.model.game.Game;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.piece.PieceType;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.ChessBoardReplayModeLogicHandler;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardRefresher;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoard;

import java.util.LinkedList;

public class ReplayMainGameController implements IMainGameController {

  private Controller controller;

  private LinkedList<String> unparsedMoves;

  private ChessBoard chessBoard;

  @Override
  public void startGame(Stage primaryStage) {

    controller = new Controller(GameMode.GAME_REPLAY);
    controller.startNewGame(GameMode.GAME_REPLAY);
    chessBoard = ChessBoardFactory.CreateBoardForReplay(this);
    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.GAME_REPLAY);
    primaryStage.setTitle("Plumbes Chess Replay Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public Game getGame() {
    return controller.getGame();
  }

  @Override
  public void doMove(BoardCell boardCell) {

    // boardCell is null
    if (unparsedMoves != null && unparsedMoves.size() > 0) {
      String unparsedMove = unparsedMoves.pop();

      Move move = AGNMoveParser.parseAGNMove(unparsedMove, getGame());

      boardCell =
          chessBoard.getBoardCells()[move.getNewPosition().getX()][move.getNewPosition().getY()];

      chessBoard.setChosenPosition(move.getOldPosition());

      if (AGNMoveParser.isPromotion(unparsedMove)) {

        PieceType promotionResult = AGNMoveParser.getPromotionResult(unparsedMove);

        ChessBoardReplayModeLogicHandler.performMoveWithPromotion(
            chessBoard, boardCell, controller, promotionResult);
      }

      ChessBoardReplayModeLogicHandler.performMove(chessBoard, boardCell, controller);
    }
  }

  @Override
  public Game startNewGame() {
    this.unparsedMoves = new LinkedList<>();

    return controller.startNewGame();
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {

    Game game = startNewGame();

    ChessBoardRefresher.refreshBoard(chessBoard, game);
    this.unparsedMoves =
        new LinkedList<>(AGNGameParser.parseAGNGame(gameMetaInformation.getGameText()));
  }
}
