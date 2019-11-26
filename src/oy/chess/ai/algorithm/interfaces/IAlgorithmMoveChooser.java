package oy.chess.ai.algorithm.interfaces;

import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.algorithm.model.AlgorithmResult;

import java.util.List;

public interface IAlgorithmMoveChooser {
    AlgorithmResult chooseBestMove(MinMaxEnum currentMinMax, List<AlgorithmResult> moves);
}
