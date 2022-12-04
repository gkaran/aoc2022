package org.gkaran.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {

    String input =
            """
            1000
            2000
            3000
            
            4000
            
            5000
            6000
            
            7000
            8000
            9000
            
            10000
            """;

    @Test
    void solvePartA() {
        Assertions.assertEquals("24000", new Day01().solvePartA(input));
    }

    @Test
    void solvePartB() {
        Assertions.assertEquals("45000", new Day01().solvePartB(input));
    }

}