package org.gkaran.challenge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.geom.Point2D;
import java.util.*;

public class Day09 implements Day<Integer, Integer> {
    @Override
    public Integer solvePartA(String input) {
        return solveForKnots(input, 2);
    }

    @Override
    public Integer solvePartB(String input) {
        return solveForKnots(input, 10);
    }

    private int solveForKnots(String input, int numberOfKnots) {
        var moves = Arrays.stream(input.split("\n"))
                .map(s -> s.split(" "))
                .<List<Direction>>mapMulti((i, consumer) -> consumer.accept(Collections.nCopies(Integer.parseInt(i[1]), Direction.getForAbbreviation(i[0]))))
                .flatMap(List::stream)
                .toList();

        var tailEntries = new HashSet<Point2D>();
        var knots = new ArrayList<>(Collections.nCopies(numberOfKnots, new Point2D.Double(0, 0)));
        tailEntries.add(knots.get(numberOfKnots - 1));

        for (var move : moves) {
            var head = knots.get(0);
            knots.set(0, switch (move) {
                case UP -> new Point2D.Double(head.getX(), head.getY() + 1);
                case DOWN -> new Point2D.Double(head.getX(), head.getY() - 1);
                case LEFT -> new Point2D.Double(head.getX() - 1, head.getY());
                case RIGHT -> new Point2D.Double(head.getX() + 1, head.getY());
            });
            for (var i = 1; i < numberOfKnots; i++){
                knots.set(i, getNewKnotLocation(knots.get(i-1), knots.get(i)));
            }
            tailEntries.add(knots.get(numberOfKnots-1));
        }
        return tailEntries.size();
    }

    @RequiredArgsConstructor
    @Getter
    private enum Direction {
        UP("U"), DOWN("D"), LEFT("L"), RIGHT("R");
        private final String abbreviation;

        public static Direction getForAbbreviation(String abbreviation) {
            return Arrays.stream(Direction.values()).filter(v -> v.getAbbreviation().equals(abbreviation)).findFirst().orElseThrow();
        }
    }

    private Point2D.Double getNewKnotLocation(Point2D.Double head, Point2D.Double tail) {
        if (head.distance(tail) >= 2.0) {
            int deltaX = 0;
            int deltaY = 0;
            if (head.getX() == tail.getX()) {
                deltaY = head.getY() > tail.getY() ? 1 : -1;
            } else if (head.getY() == tail.getY()) {
                deltaX = head.getX() > tail.getX() ? 1 : -1;
            } else {
                if(head.getX() > tail.getX() && head.getY() > tail.getY()) { // TOP RIGHT
                    deltaX = 1;
                    deltaY = 1;
                } else if (head.getX() < tail.getX() && head.getY() > tail.getY()) { // TOP LEFT
                    deltaX = -1;
                    deltaY = 1;
                } else if (head.getX() > tail.getX() && head.getY() < tail.getY()) { // BOTTOM RIGHT
                    deltaX = 1;
                    deltaY = -1;
                } else { // BOTTOM LEFT
                    deltaX = -1;
                    deltaY = -1;
                }
            }

            return new Point2D.Double(tail.getX() + deltaX, tail.getY() + deltaY);
        }
        return tail;
    }
}
