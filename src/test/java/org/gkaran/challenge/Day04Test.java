package org.gkaran.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    String input = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """;

    @Test
    void solvePartA() {
        Assertions.assertEquals(2, new Day04().solvePartA(input));
    }

    @Test
    void solvePartB() {
        Assertions.assertEquals(4, new Day04().solvePartB(input));
    }
}