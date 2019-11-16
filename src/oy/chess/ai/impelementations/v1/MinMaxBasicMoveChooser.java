package oy.chess.ai.impelementations.v1;

import oy.chess.ai.minmax.interfaces.IMinMaxBestMoveChooser;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.minmax.model.MinMaxResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinMaxBasicMoveChooser implements IMinMaxBestMoveChooser {
  @Override
  public MinMaxResult chooseBestMove(MinMaxEnum currentMinMax, List<MinMaxResult> minMaxResults) {
    // Chooses best move according to current min or max.
    MinMaxResult bestResult;
    if (currentMinMax == MinMaxEnum.MAX)
      bestResult = Collections.max(minMaxResults, Comparator.comparing(MinMaxResult::getScore));
    else bestResult = Collections.min(minMaxResults, Comparator.comparing(MinMaxResult::getScore));

    return bestResult;
  }
}
