package oy.chess.view.boardlogic.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oy.chess.model.piece.PieceType;
import oy.chess.model.player.PlayerColor;
import oy.chess.view.cell.ImagePathGetter;

import java.util.Optional;

class ChessBoardPromotionPopupController {

  static boolean isPlayAgainCheckMate(PlayerColor currentPlayerColor) {
    String winner = currentPlayerColor == PlayerColor.WHITE ? "Black" : "White";

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

  static PieceType getPromotionResult(PlayerColor currentPlayerColor) {

    ImageView queenImg =
        new ImageView(new Image(ImagePathGetter.getImage(PieceType.QUEEN, currentPlayerColor)));

    ImageView bishopImg =
        new ImageView(new Image(ImagePathGetter.getImage(PieceType.BISHOP, currentPlayerColor)));

    ImageView rookImg =
        new ImageView(new Image(ImagePathGetter.getImage(PieceType.ROOK, currentPlayerColor)));

    ImageView knightImg =
        new ImageView(new Image(ImagePathGetter.getImage(PieceType.KNIGHT, currentPlayerColor)));
    ChoiceDialog<ImageView> choiceDialog =
        new ChoiceDialog<>(knightImg, bishopImg, rookImg, queenImg);

    Optional<ImageView> promotionResult = choiceDialog.showAndWait();
    if (promotionResult.isPresent()) {

      ImageView result = promotionResult.get();

      if (result.equals(knightImg)) {
        return PieceType.KNIGHT;
      } else if (result.equals(bishopImg)) {
        return PieceType.BISHOP;
      } else if (result.equals(rookImg)) {
        return PieceType.ROOK;
      } else if (result.equals(queenImg)) {
        return PieceType.QUEEN;
      }
    }
    return null;
  }
}
