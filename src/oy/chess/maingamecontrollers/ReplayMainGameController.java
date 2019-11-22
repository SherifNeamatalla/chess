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
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardRefresher;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;

import java.util.LinkedList;

public class ReplayMainGameController extends AbstractMainGameController {

  private LinkedList<String> unparsedMoves;

  @Override
  public void startGame(Stage primaryStage) {

    super.startGame(primaryStage);
    this.chessBoard = ChessBoardFactory.CreateBoardForReplay(this);

    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.GAME_REPLAY);
    primaryStage.setTitle("Plumbes Chess Replay Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public void doMove(BoardCell boardCell) {

    // boardCell is null
    if (unparsedMoves != null && unparsedMoves.size() > 0) {
      String unparsedMove = unparsedMoves.pop();

      Move move = AGNMoveParser.parseAGNMove(unparsedMove, getGame());

      chessBoard.setChosenPosition(move.getOldPosition());

      if (AGNMoveParser.isPromotion(unparsedMove)) {

        PieceType promotionResult = AGNMoveParser.getPromotionResult(unparsedMove);

        game.setPromotionResult(new Piece(1, null, promotionResult, null, true));
      }

      MoveResult moveResult = this.controller.doMove(move, this.game);

      if (moveResult.isSuccess()) {
        this.game = moveResult.getGame();

        ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, this.game);
      }
    }
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {

    Game game = startNewGame();

    ChessBoardRefresher.refreshBoard(chessBoard, game);
    this.unparsedMoves =
        new LinkedList<>(AGNGameParser.parseAGNGame(gameMetaInformation.getGameText()));
  }
}
