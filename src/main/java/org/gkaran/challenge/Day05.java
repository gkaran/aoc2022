package org.gkaran.challenge;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05 implements Day<String, String> {
    @Override
    public String solvePartA(String input) {
        var rows = input.split("\n");
        var stacks = getStacks(rows);
        getMoves(rows).forEach(m -> {
            for (int i = 0; i < m[0]; i++) {
                stacks.get(m[2] - 1).push(stacks.get(m[1] - 1).remove());
            }
        });

        return getResult(stacks);
    }

    @Override
    public String solvePartB(String input) {
        var rows = input.split("\n");
        var stacks = getStacks(rows);
        Deque<String> temp = new ArrayDeque<>();
        getMoves(rows).forEach(m -> {
            // TODO: Not the best approach. Try to revisit in the future
            for (int i = 0; i < m[0]; i++) {
                temp.push(stacks.get(m[1] - 1).remove());
            }
            for (int i = 0; i < m[0]; i++) {
                stacks.get(m[2] - 1).push(temp.remove());
            }
        });
        return getResult(stacks);
    }

    private List<Deque<String>> getStacks(String[] rows) {
        var initialState = Arrays.stream(rows)
                .takeWhile(StringUtils::isNotBlank)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(initialState);

        return initialState.stream()
                .skip(1)
                .map(s -> Arrays.stream(s.split("(?<=\\G.{" + 4 + "})")).map(StringUtils::trimToEmpty).map(t -> StringUtils.replaceChars(t, "[]", "")).toList())
                .collect(Collector.of(
                        ArrayList<Deque<String>>::new,
                        (list, value) -> {
                            for (int i = 0; i < value.size(); i++) {
                                if (list.size() <= i) list.add(new ArrayDeque<>());
                                var stack = list.get(i);
                                if (StringUtils.isNotBlank(value.get(i))) stack.push(value.get(i));
                            }
                        },
                        (r1, r2) -> {
                            throw new UnsupportedOperationException("Parallel not supported");
                        }
                ));
    }

    private Stream<Integer[]> getMoves(String[] rows) {
        return Arrays.stream(rows)
                .dropWhile(s -> !s.startsWith("move"))
                .map(m -> Arrays.stream(m.replace("move ", "").split("\\D+"))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
    }

    private String getResult(List<Deque<String>> stacks) {
        return stacks.stream().map(Deque::remove).collect(Collectors.joining());
    }
}
