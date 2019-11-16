package oy.chess.ai.minmax.interfaces;

import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.minmax.model.MinMaxResult;
import oy.chess.model.move.Move;

import java.util.List;

public interface IMinMaxBestMoveChooser {
    MinMaxResult chooseBestMove(MinMaxEnum currentMinMax, List<MinMaxResult> moves);
}
