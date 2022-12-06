package org.gkaran.challenge;

import org.apache.commons.lang3.CharUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day03 implements Day<Integer, Integer> {
    @Override
    public Integer solvePartA(String input) {
        return Arrays.stream(input.split("\n"))
                .mapToInt(this::getRucksackPriority)
                .sum();
    }

    @Override
    public Integer solvePartB(String input) {
        return Arrays.stream(input.split("\n"))
                .collect(pagingCollector(3))
                .stream()
                .mapToInt(this::getGroupPriority)
                .sum();
    }

    private int getRucksackPriority(String rucksack) {
        var first = toCharSet(rucksack.substring(0, rucksack.length() / 2));
        var second = toCharSet(rucksack.substring(rucksack.length() / 2));
        first.retainAll(second);
        return first.stream().mapToInt(this::getPriority).sum();
    }

    private int getGroupPriority(List<String> group) {
        return group.stream()
                .map(this::toCharSet)
                .reduce((elf1, elf2) -> {
                    elf1.retainAll(elf2);
                    return elf1;
                })
                .map(chars -> chars.stream().mapToInt(this::getPriority).sum())
                .orElse(0);
    }

    private <T> Collector<T, List<List<T>>, List<List<T>>> pagingCollector(int pageSize) {
        return Collector.of(
                ArrayList::new,
                (list, value) -> {
                    var block = (list.isEmpty() ? null : list.get(list.size() - 1));
                    if (block == null || block.size() == pageSize) {
                        list.add(block = new ArrayList<>(pageSize));
                    }
                    block.add(value);
                },
                (r1, r2) -> {
                    throw new UnsupportedOperationException("Parallel not supported");
                }
        );
    }

    private Set<Character> toCharSet(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    }

    private int getPriority(char c) {
        return CharUtils.isAsciiAlphaLower(c) ? 1 + (c - 'a') : 27 + (c - 'A');
    }


}
