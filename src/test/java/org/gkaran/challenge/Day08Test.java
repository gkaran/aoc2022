package org.gkaran.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    String input = """
            30373
            25512
            65332
            33549
            35390
            """;

    @Test
    void solvePartA() {
        assertEquals(21, new Day08().solvePartA(input));
    }

    @Test
    void solvePartB() {
        assertEquals(8, new Day08().solvePartB(input));
    }
}