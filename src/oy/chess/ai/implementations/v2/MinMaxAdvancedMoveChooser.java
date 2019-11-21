package oy.chess.ai.implementations.v2;

import oy.chess.ai.minmax.interfaces.IMinMaxBestMoveChooser;
import oy.chess.ai.minmax.model.MinMaxEnum;
import oy.chess.ai.minmax.model.MinMaxResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinMaxAdvancedMoveChooser implements IMinMaxBestMoveChooser {
    @Override
    public MinMaxResult chooseBestMove(MinMaxEnum currentMinMax, List<MinMaxResult> moves) {


        MinMaxResult bestResult;
        if (currentMinMax == MinMaxEnum.MAX)
            bestResult = Collections.max(moves, Comparator.comparing(MinMaxResult::getScore));
        else bestResult = Collections.min(moves, Comparator.comparing(MinMaxResult::getScore));

        return bestResult;
    }
}
