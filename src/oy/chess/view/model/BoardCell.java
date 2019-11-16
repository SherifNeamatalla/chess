package oy.chess.view.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oy.chess.model.piece.Piece;
import oy.chess.model.position.Position;
import oy.chess.view.cell.ImagePathGetter;

import java.io.InputStream;

public class BoardCell extends Button {
  private Position position;
  private Piece piece; // piece currently on cell
  private boolean isChosen = false;

  public BoardCell(boolean light, Position position) {
    super();
    this.getStylesheets().add("./oy/chess/assets/stylesheet.css");
    this.position = position;
    this.piece = null;
    this.getStyleClass().add("chess-cell");
    this.setColor(light);
  }

  private void setColor(boolean light) {
    if (light) this.getStyleClass().add("chess-cell-light");
    else this.getStyleClass().add("chess-cell-dark");
  }

  public void setColorChecked(boolean setChecked) {
    if (setChecked) this.getStyleClass().add("chess-cell-check");
    else this.getStyleClass().remove("chess-cell-check");
  }

  public Piece getPiece() {
    return this.piece;
  }

  // Sets the piece, draws image of piece on cell
  public void setPiece(Piece piece) {
    this.piece = piece;

    if (this.piece != null) {
      InputStream is;
      Image img =
          new Image(
              ImagePathGetter.getImage(
                  this.piece.getPieceType(), this.piece.getOwnerPlayerColor()));
      ImageView imageView = new ImageView(img);
      this.setGraphic(imageView);

    } else this.setGraphic(new ImageView());
  }

  public boolean isChosen() {
    return isChosen;
  }

  public void setChosen(boolean chosen) {
    if (chosen) this.getStyleClass().add("chess-cell-active");
    else this.getStyleClass().remove("chess-cell-active");

    isChosen = chosen;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
