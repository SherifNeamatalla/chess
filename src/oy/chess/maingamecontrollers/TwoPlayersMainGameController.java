package oy.chess.maingamecontrollers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import oy.chess.model.game.GameMetaInformation;
import oy.chess.model.game.GameMode;
import oy.chess.model.move.Move;
import oy.chess.model.move.MoveResult;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.ProgramWindow;
import oy.chess.view.boardlogic.util.ChessBoardFactory;
import oy.chess.view.boardlogic.util.ChessBoardLogicCommonFunctions;
import oy.chess.view.boardlogic.util.ChessBoardTurnChanger;
import oy.chess.view.model.BoardCell;
import oy.chess.view.model.ChessBoardState;

public class TwoPlayersMainGameController extends AbstractMainGameController {

  public void startGame(Stage primaryStage) {

    super.startGame(primaryStage);
    this.chessBoard = ChessBoardFactory.createBoard(this);

    ProgramWindow window = ProgramWindow.getInstance(chessBoard, this, GameMode.HUMAN_VS_HUMAN);
    primaryStage.setTitle("Plumbes Chess PvP Mode");
    primaryStage.setScene(new Scene(window));
    primaryStage.show();
  }

  @Override
  public void doMove(BoardCell boardCell) {
    boolean isGameOverAndPlayAgain = false;

    if (chessBoard.getCurrentState() == ChessBoardState.TURN_START
        && boardCell.getPiece() != null) {

      ChessBoardLogicCommonFunctions.setPieceChosenState(chessBoard, boardCell);

    } else if (chessBoard.getCurrentState() == ChessBoardState.PIECE_CHOSEN) {

      Move move = new Move(this.chessBoard.getChosenPosition(), boardCell.getPosition());
      if (isPromotionMove(move, this.chessBoard.getChosenCell().getPiece())) {

        PieceType promotionResult =
            ChessBoardTurnChanger.getPromotionResult(game.getCurrentPlayerColor());

        game.setPromotionResult(new Piece(1, null, promotionResult, null, true));
      }

      MoveResult moveResult = this.controller.doMove(move, this.game);

      if (moveResult.isSuccess()) {
        this.game = moveResult.getGame();

        isGameOverAndPlayAgain =
            ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, this.game);
      }
      if (isGameOverAndPlayAgain) this.game = controller.startNewGame();
      ChessBoardTurnChanger.changeTurnAndRefreshBoardState(chessBoard, this.game);
    }
  }

  private boolean isPromotionMove(Move move, Piece chosenPiece) {
    if (chosenPiece == null || chosenPiece.getPieceType() != PieceType.PAWN) return false;

    // Movement validation will not be done here, it is assumed that the move is already correct.

    // Checks if it's last row.
    int lastX = chosenPiece.getOwnerPlayerColor() == PlayerColor.WHITE ? 0 : 7;

    return move.getNewPosition().getX() == lastX;
  }

  @Override
  public void uploadGame(GameMetaInformation gameMetaInformation) {
    // TODO after save is implemented.
  }
}
