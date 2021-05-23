package com.ssd.advent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.advent.model.Passport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/4
 */
public class Day4 {
    public static void main(String[] args) throws IOException {
        IntPredicate validByr = n -> n >= 1920 && n <= 2002;
        IntPredicate validIyr = n -> n >= 2010 && n <= 2020;
        IntPredicate validEyr = n -> n >= 2020 && n <= 2030;
        Predicate<String> validHgt = s -> {
            if (s.contains("cm") || s.contains("in")) {
                Integer num = Integer.parseInt(s.substring(0, s.length() - 2));
                return (s.contains("cm") && num >= 150 && num <= 193) || (s.contains("in") && num >= 59 && num <= 76);
            }
            return false;
        };
        var validHcl = Pattern.compile("#([0-9a-f]{6})").asMatchPredicate();
        var validEcl = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth").asMatchPredicate();
        var validPid = Pattern.compile("\\d{9}").asMatchPredicate();

        String inputFile = Files.readString(Path.of("src/main/resources/file4.txt"), StandardCharsets.US_ASCII);
        Scanner lines = new Scanner(inputFile).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));

        List<String> stringList = new ArrayList<>();
        while (lines.hasNext()) {
            stringList.add(lines.next().trim().replaceAll("\\s+", " "));
        }

        List<String> collect = stringList.stream()
                .map(i -> i.replaceAll(":", "\":\""))
                .map(i -> i.replaceAll(" ", "\" , \""))
                .map(i -> "{\"" + i + "\"}")
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Passport> collect1 = collect.stream()
                .map(p -> {
                    try {
                        return objectMapper.readValue(p, Passport.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        List<Passport> output1 = collect1.stream()
                .filter(p -> p.getByr() != null && p.getIyr() != null && p.getEyr() != null && p.getHgt() != null
                        && p.getHcl() != null && p.getEcl() != null && p.getPid() != null)
                .collect(Collectors.toList());

        System.out.println("Part 1 Output :"+ (long)output1.size());

//      *****************************************************************************

        List<Passport> output2 = collect1.stream()
                .filter(p -> p.getByr() != null && p.getIyr() != null && p.getEyr() != null && p.getHgt() != null
                        && p.getHcl() != null && p.getEcl() != null && p.getPid() != null)
                .filter(p -> validByr.test(p.getByr()))
                .filter(p -> validIyr.test(p.getIyr()))
                .filter(p -> validEyr.test(p.getEyr()))
                .filter(p -> validHgt.test(p.getHgt()))
                .filter(p -> validHcl.test(p.getHcl()))
                .filter(p -> validEcl.test(p.getEcl()))
                .filter(p -> validPid.test(p.getPid()))
                .collect(Collectors.toList());

        System.out.println("Part 2 Output :"+ (long)output2.size());
    }
}
