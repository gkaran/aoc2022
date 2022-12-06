package org.gkaran.challenge;

import java.util.Arrays;
import java.util.Map;

public class Day02 implements Day<Integer, Integer> {

    private static final int ROCK_SCORE = 1;
    private static final int PAPER_SCORE = 2;
    private static final int SCISSORS_SCORE = 3;
    private static final int WIN_SCORE = 6;
    private static final int DRAW_SCORE = 3;
    private static final int LOSE_SCORE = 0;

    private static final String ROCK = "A";
    private static final String PAPER = "B";
    private static final String SCISSORS = "C";

    @Override
    public Integer solvePartA(String input) {
        var combinations = Map.of(
                ROCK + " X", DRAW_SCORE + ROCK_SCORE,
                ROCK + " Y", WIN_SCORE + PAPER_SCORE,
                ROCK + " Z", LOSE_SCORE + SCISSORS_SCORE,
                PAPER + " X", LOSE_SCORE + ROCK_SCORE,
                PAPER + " Y", DRAW_SCORE + PAPER_SCORE,
                PAPER + " Z", WIN_SCORE + SCISSORS_SCORE,
                SCISSORS + " X", WIN_SCORE + ROCK_SCORE,
                SCISSORS + " Y", LOSE_SCORE + PAPER_SCORE,
                SCISSORS + " Z", DRAW_SCORE + SCISSORS_SCORE
        );
        return Arrays.stream(input.split("\n")).mapToInt(combinations::get).sum();
    }

    @Override
    public Integer solvePartB(String input) {
        var combinations = Map.of(
                ROCK + " X", LOSE_SCORE + SCISSORS_SCORE,
                ROCK + " Y", DRAW_SCORE + ROCK_SCORE,
                ROCK + " Z", WIN_SCORE + PAPER_SCORE,
                PAPER + " X", LOSE_SCORE + ROCK_SCORE,
                PAPER + " Y", DRAW_SCORE + PAPER_SCORE,
                PAPER + " Z", WIN_SCORE + SCISSORS_SCORE,
                SCISSORS + " X", LOSE_SCORE + PAPER_SCORE,
                SCISSORS + " Y", DRAW_SCORE + SCISSORS_SCORE,
                SCISSORS + " Z", WIN_SCORE + ROCK_SCORE
        );
        return Arrays.stream(input.split("\n")).mapToInt(combinations::get).sum();
    }
}
