package org.gkaran.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    String input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
            """;

    @Test
    void solvePartA() {
        assertEquals(13, new Day09().solvePartA(input));
    }

    @Test
    void solvePartB() {
        assertEquals(1, new Day09().solvePartB(input));
    }

    @Test
    void solvePartB_Case2() {
        assertEquals(36, new Day09().solvePartB("""
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
                """));
    }
}