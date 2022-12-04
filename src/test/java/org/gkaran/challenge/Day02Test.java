package org.gkaran.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

    @Test
    void solvePartA() {
        String input = """
                A Y
                B X
                C Z
                """;
        Assertions.assertEquals(15, new Day02().solvePartA(input));
    }

    @Test
    void solvePartB() {
        String input = """
                A Y
                B X
                C Z
                """;
        Assertions.assertEquals(12, new Day02().solvePartB(input));
    }


}