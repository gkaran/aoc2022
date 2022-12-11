package org.gkaran.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    String input = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """;

    @Test
    void solvePartA() {
        assertEquals(95437, new Day07().solvePartA(input));
    }

    @Test
    void solvePartB() {
        assertEquals(24933642, new Day07().solvePartB(input));
    }

}