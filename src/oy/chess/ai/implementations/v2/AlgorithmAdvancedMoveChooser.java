package oy.chess.ai.implementations.v2;

import oy.chess.ai.algorithm.interfaces.IAlgorithmMoveChooser;
import oy.chess.ai.algorithm.model.AlgorithmResult;
import oy.chess.ai.minmax.model.MinMaxEnum;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlgorithmAdvancedMoveChooser implements IAlgorithmMoveChooser {
  @Override
  public AlgorithmResult chooseBestMove(MinMaxEnum currentMinMax, List<AlgorithmResult> results) {

    AlgorithmResult bestResult;
    if (currentMinMax == MinMaxEnum.MAX)
      bestResult = Collections.max(results, Comparator.comparing(AlgorithmResult::getScore));
    else bestResult = Collections.min(results, Comparator.comparing(AlgorithmResult::getScore));

    return bestResult;
  }
}
