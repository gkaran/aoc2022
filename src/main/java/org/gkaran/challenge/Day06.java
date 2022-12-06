package org.gkaran.challenge;

import java.util.Arrays;
import java.util.HashSet;

public class Day06 implements Day<Integer, Integer> {
    @Override
    public Integer solvePartA(String input) {
        return getStartOfMarker(input, 4);
    }

    @Override
    public Integer solvePartB(String input) {
        return getStartOfMarker(input, 14);
    }

    private int getStartOfMarker(String input, int distinctPoints) {
        for (var i = distinctPoints; i < input.length() - 1; i++) {
            if (new HashSet<>(Arrays.asList(input.substring(i - distinctPoints, i).split(""))).size() == distinctPoints) {
                return i;
            }
        }
        return -1;
    }
}
