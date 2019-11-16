package oy.chess.view.boardlogic.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oy.chess.model.game.Game;
import oy.chess.model.piece.Piece;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.cell.ImagePathGetter;

class ChessBoardPromotionPopupController {

  static boolean isPlayAgainCheckMate(Game game) {
    String winner = game.getCurrentPlayerColor() == PlayerColor.WHITE ? "Black" : "White";

    Alert playAgainAlert =
        new Alert(
            Alert.AlertType.NONE, winner + " wins!, would you like to play again?", ButtonType.OK);

    playAgainAlert
        .showAndWait()
        .ifPresent(
            response -> {
              if (response == ButtonType.OK) {

              } else Platform.exit();
            });

    return true;
  }

  static boolean isPlayAgainStaleMate() {

    Alert playAgainAlert =
        new Alert(
            Alert.AlertType.NONE,
            "It's a stalemate!, would you like to play again?",
            ButtonType.OK);

    playAgainAlert
        .showAndWait()
        .ifPresent(
            response -> {
              if (response == ButtonType.OK) {

              } else Platform.exit();
            });

    return true;
  }

  static void setPromotionResult(Game game) {

    ImageView queenImg =
        new ImageView(
            new Image(ImagePathGetter.getImage(PieceType.QUEEN, game.getCurrentPlayerColor())));

    ImageView bishopImg =
        new ImageView(
            new Image(ImagePathGetter.getImage(PieceType.BISHOP, game.getCurrentPlayerColor())));

    ImageView rookImg =
        new ImageView(
            new Image(ImagePathGetter.getImage(PieceType.ROOK, game.getCurrentPlayerColor())));

    ImageView knightImg =
        new ImageView(
            new Image(ImagePathGetter.getImage(PieceType.KNIGHT, game.getCurrentPlayerColor())));
    ChoiceDialog<ImageView> choiceDialog =
        new ChoiceDialog<>(knightImg, bishopImg, rookImg, queenImg);

    choiceDialog
        .showAndWait()
        .ifPresent(
            e -> {
              if (e.equals(knightImg)) {
                game.setPromotionResult(new Piece(1, null, PieceType.KNIGHT, null, true));
              } else if (e.equals(bishopImg)) {
                game.setPromotionResult(new Piece(1, null, PieceType.BISHOP, null, true));
              } else if (e.equals(rookImg)) {
                game.setPromotionResult(new Piece(1, null, PieceType.ROOK, null, true));
              } else if (e.equals(queenImg)) {
                game.setPromotionResult(new Piece(1, null, PieceType.QUEEN, null, true));
              }
            });
  }
}
