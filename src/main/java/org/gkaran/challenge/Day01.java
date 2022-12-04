package org.gkaran.challenge;

import java.util.Arrays;
import java.util.Comparator;

public class Day01 implements Day {
    @Override
    public String solvePartA(String input) {
        return Arrays.stream(input.split("\n\n"))
                .mapToLong(this::sumForElf)
                .max()
                .orElseThrow() + "";
    }

    @Override
    public String solvePartB(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(this::sumForElf)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Long::longValue)
                .sum() + "";
    }

    private Long sumForElf(String elfCalories) {
        return Arrays.stream(elfCalories.split("\n"))
                .mapToLong(Long::parseLong)
                .sum();
    }
}
