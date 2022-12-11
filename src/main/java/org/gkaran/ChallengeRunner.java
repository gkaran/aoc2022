package org.gkaran;

import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.gkaran.challenge.*;

import java.util.Scanner;

public class ChallengeRunner {

    public static void main(String[] args) {
        Options options = new Options();
        options.addRequiredOption("d", "day", true, "Day to solve");
        options.addRequiredOption("p", "part", true, "Part to solve");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);
            String dayArg = StringUtils.leftPad(line.getOptionValue("day"), 2, '0');
            Day day = switch (dayArg) {
                case "01" -> new Day01();
                case "02" -> new Day02();
                case "03" -> new Day03();
                case "04" -> new Day04();
                case "05" -> new Day05();
                case "06" -> new Day06();
                case "07" -> new Day07();
                default -> throw new RuntimeException("invalid day number");
            };

            var scanner = new Scanner(ChallengeRunner.class.getResourceAsStream("/day" + dayArg + ".txt"));
            String input = scanner.useDelimiter("\\A").next();
            scanner.close();

            String partArg = line.getOptionValue("part");
            var solution = switch (partArg) {
                case "a", "A" -> day.solvePartA(input);
                case "b", "B" -> day.solvePartB(input);
                default -> throw new RuntimeException("invalid day part");
            };

            System.out.println("Result for day " + dayArg + " and part " + partArg + ": " + solution);

        } catch (RuntimeException | ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
            System.exit(1);
        }
    }
}
