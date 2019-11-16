package oy.chess.annotation.parsing;

import oy.chess.model.game.GameMetaInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileReader {

  private static final String INSIDE_BRACKETS_DELIMITER = "\\s\"(.*)\"]";

  private static final String EMPTY_STRING_REGEX = "\\s+";
  private static final String NOT_GAME_STRING_REGEX = "\\[.*?]";
  private static final String EVENT_REGEX = "^\\[Event.*?]";
  private static final String SITE_REGEX = "^\\[Site.*?]";
  private static final String DATE_REGEX = "^\\[Date.*?]";
  private static final String ROUND_REGEX = "^\\[Round" + INSIDE_BRACKETS_DELIMITER;
  private static final String WHITE_PLAYER_REGEX = "^\\[White" + INSIDE_BRACKETS_DELIMITER;
  private static final String BLACK_PLAYER_REGEX = "^\\[Black" + INSIDE_BRACKETS_DELIMITER;
  private static final String WHITE_PLAYER_ELO_REGEX = "^\\[WhiteElo" + INSIDE_BRACKETS_DELIMITER;
  private static final String BLACK_PLAYER_ELO_REGEX = "^\\[BlackElo" + INSIDE_BRACKETS_DELIMITER;

  static List<GameMetaInformation> readFile(File file) {

    List<GameMetaInformation> result = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(file);
      StringBuilder text = new StringBuilder();
      GameMetaInformation newGame = new GameMetaInformation();

      boolean firstTimeFlag = true;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        // To restart as this is the delimeter
        if (line.matches(EVENT_REGEX)) {
          if (!firstTimeFlag) {
            newGame.setGameText(text.toString());
            newGame.setEvent(line);
            text = new StringBuilder();
            result.add(newGame);
            newGame = new GameMetaInformation();
          } else {
            text = new StringBuilder();
            newGame = new GameMetaInformation();
            firstTimeFlag = false;
          }
        } else {
          parseLine(line, text, newGame);
        }
      }

    } catch (FileNotFoundException ignored) {
    }
    return result;
  }

  private static void parseLine(
      String line, StringBuilder text, GameMetaInformation gameMetaInformation) {

    Matcher matcher;
    if (line.matches(EMPTY_STRING_REGEX)) return;

    if (line.matches(WHITE_PLAYER_ELO_REGEX)) {

      matcher = Pattern.compile(WHITE_PLAYER_ELO_REGEX).matcher(line);
      if (matcher.find()) gameMetaInformation.setWhiteElo(matcher.group(1));

    } else if (line.matches(BLACK_PLAYER_ELO_REGEX)) {

      matcher = Pattern.compile(BLACK_PLAYER_ELO_REGEX).matcher(line);
      if (matcher.find()) gameMetaInformation.setBlackElo(matcher.group(1));
    }
    if (line.matches(WHITE_PLAYER_REGEX)) {

      matcher = Pattern.compile(WHITE_PLAYER_REGEX).matcher(line);
      if (matcher.find()) gameMetaInformation.setWhitePlayer(matcher.group(1));

    } else if (line.matches(ROUND_REGEX)) {

      matcher = Pattern.compile(ROUND_REGEX).matcher(line);
      if (matcher.find()) gameMetaInformation.setRound(matcher.group(1));

    } else if (line.matches(BLACK_PLAYER_REGEX)) {

      matcher = Pattern.compile(BLACK_PLAYER_REGEX).matcher(line);
      if (matcher.find()) gameMetaInformation.setBlackPlayer(matcher.group(1));

    } else if (line.matches(NOT_GAME_STRING_REGEX)) return;
    else text.append(" ").append(line);
  }
}
