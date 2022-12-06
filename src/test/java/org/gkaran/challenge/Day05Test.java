package org.gkaran.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

    String input = """
                [D]
            [N] [C]
            [Z] [M] [P]
             1   2   3
                            
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
            """;

    @Test
    void solvePartA() {
        Assertions.assertEquals("CMZ", new Day05().solvePartA(input));
    }

    @Test
    void solvePartB() {
        Assertions.assertEquals("MCD", new Day05().solvePartB(input));
    }
}