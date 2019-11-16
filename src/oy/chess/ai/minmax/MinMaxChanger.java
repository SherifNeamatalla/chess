package oy.chess.ai.minmax;

import oy.chess.ai.minmax.model.MinMaxEnum;

class MinMaxChanger {

  static MinMaxEnum changeMinMax(MinMaxEnum minMaxEnum) {
    if (minMaxEnum == MinMaxEnum.MAX) {
      return MinMaxEnum.MIN;
    }
    return MinMaxEnum.MAX;
  }
}
