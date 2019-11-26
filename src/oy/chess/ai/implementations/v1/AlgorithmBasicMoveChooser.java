package oy.chess.ai.implementations.v1;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.algorithm.model.AlgorithmResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlgorithmBasicMoveChooser implements IAlgorithmMoveChooser {
  @Override
  public AlgorithmResult chooseBestMove(MinMaxEnum currentMinMax, List<AlgorithmResult> algorithmResults) {
    // Chooses best move according to current min or max.
    AlgorithmResult bestResult;
    if (currentMinMax == MinMaxEnum.MAX)
      bestResult = Collections.max(algorithmResults, Comparator.comparing(AlgorithmResult::getScore));
    else bestResult = Collections.min(algorithmResults, Comparator.comparing(AlgorithmResult::getScore));

    return bestResult;
  }
}
