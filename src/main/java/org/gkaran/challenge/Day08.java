package org.gkaran.challenge;

import java.util.Arrays;

public class Day08 implements Day<Integer, Integer> {
    @Override
    public Integer solvePartA(String input) {
        var trees = getTrees(input);

        var visibleTrees = (trees.length * 2 + trees[0].length * 2) - 4;
        for (var i = 1; i < trees.length - 1; i++) {
            for (var j = 1; j < trees[i].length - 1; j++) {
                if (isVisibleFromBottom(i, j, trees) || isVisibleFromLeft(i, j, trees) || isVisibleFromRight(i, j, trees) || isVisibleFromTop(i, j, trees)) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

    private boolean isVisibleFromTop(int y, int x, int[][] trees) {
        for(var yy = y - 1; yy >= 0; yy--) {
           if(trees[yy][x] >= trees[y][x]) return false;
        }

        return true;
    }

    private boolean isVisibleFromBottom(int y, int x, int[][] trees) {
        for(var yy = y + 1; yy < trees.length; yy++) {
            if(trees[yy][x] >= trees[y][x]) return false;
        }
        return true;
    }

    private boolean isVisibleFromLeft(int y, int x, int[][] trees) {
        for (var xx = x - 1; xx >= 0; xx--) {
            if (trees[y][xx] >= trees[y][x]) return false;
        }
        return true;
    }

    private boolean isVisibleFromRight(int y, int x, int[][] trees) {
        for (var xx = x + 1; xx < trees[y].length; xx++) {
            if (trees[y][xx] >= trees[y][x]) return false;
        }
        return true;
    }

    @Override
    public Integer solvePartB(String input) {
        var trees = getTrees(input);
        var max = 0;
        for (var i = 1; i < trees.length - 1; i++) {
            for (var j = 1; j < trees[i].length - 1; j++) {
                var scenicScore = getTopScore(i, j, trees) * getBottomScore(i, j, trees) * getLeftScore(i,j,trees) * getRightScore(i,j,trees);
                if (scenicScore > max) max = scenicScore;
            }
        }
        return max;
    }

    private int getTopScore(int y, int x, int[][] trees) {
        var score = 0;
        for(var yy = y - 1; yy >= 0; yy--) {
            if(trees[yy][x] < trees[y][x]) score++;
            if(trees[yy][x] >= trees[y][x]) {
                score++;
                break;
            }
        }
        return score;
    }

    private int getBottomScore(int y, int x, int[][] trees) {
        var score = 0;
        for(var yy = y + 1; yy < trees.length; yy++) {
            if(trees[yy][x] < trees[y][x]) score++;
            if(trees[yy][x] >= trees[y][x]) {
                score++;
                break;
            }
        }
        return score;
    }

    private int getLeftScore(int y, int x, int[][] trees) {
        var score = 0;
        for (var xx = x - 1; xx >= 0; xx--) {
            if (trees[y][xx] < trees[y][x]) score++;
            if (trees[y][xx] >= trees[y][x]) {
                score++;
                break;
            }
        }
        return score;
    }

    private int getRightScore(int y, int x, int[][] trees) {
        var score = 0;
        for (var xx = x + 1; xx < trees[y].length; xx++) {
            if (trees[y][xx] < trees[y][x]) score++;
            if (trees[y][xx] >= trees[y][x]) {
                score++;
                break;
            }
        }
        return score;
    }

    private int[][] getTrees(String input) {
        return Arrays.stream(input.split("\n"))
                .map(row -> Arrays.stream(row.split(""))
                        .mapToInt(i -> Integer.parseInt(i))
                        .toArray()
                ).toArray(int[][]::new);
    }
}
