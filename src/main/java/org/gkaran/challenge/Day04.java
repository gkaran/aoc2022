package org.gkaran.challenge;

import java.util.Arrays;

public class Day04 implements Day<Long, Long> {
    @Override
    public Long solvePartA(String input) {
        return Arrays.stream(input.split("\n"))
                .filter(this::isFullyOverlappingPair)
                .count();
    }

    @Override
    public Long solvePartB(String input) {
        return Arrays.stream(input.split("\n"))
                .filter(this::isOverlappingPair)
                .count();
    }

    private boolean isFullyOverlappingPair(String pair) {
        var elves = pair.split(",");
        var firstElfSections = Arrays.stream(elves[0].split("-")).map(Integer::parseInt).toArray(Integer[]::new);
        var secondElfSections = Arrays.stream(elves[1].split("-")).map(Integer::parseInt).toArray(Integer[]::new);

        return (firstElfSections[0] <= secondElfSections[0] && firstElfSections[1] >= secondElfSections[1]) ||
                (secondElfSections[0] <= firstElfSections[0] && secondElfSections[1] >= firstElfSections[1]);
    }

    private boolean isOverlappingPair(String pair) {
        var elves = pair.split(",");
        var firstElfSections = Arrays.stream(elves[0].split("-")).map(Integer::parseInt).toArray(Integer[]::new);
        var secondElfSections = Arrays.stream(elves[1].split("-")).map(Integer::parseInt).toArray(Integer[]::new);

        return (firstElfSections[0] <= secondElfSections[0] && firstElfSections[1] >= secondElfSections[0]) ||
                (firstElfSections[0] <= secondElfSections[1] && firstElfSections[1] >= secondElfSections[1]) ||
                (secondElfSections[0] <= firstElfSections[0] && secondElfSections[1] >= firstElfSections[0]) ||
                (secondElfSections[0] <= firstElfSections[1] && secondElfSections[1] >= firstElfSections[1]);
    }
}
