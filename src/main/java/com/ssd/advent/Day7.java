package com.ssd.advent;

import com.ssd.advent.util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/7
 */
public class Day7 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file7.txt");

        Map<String, List<String>> contain = inputFile.stream()
                .map(s -> s.split("bags contain"))
                .collect(Collectors.toMap(i -> i[0].trim(), i -> Arrays.stream(i[1].split(","))
                        .map(s -> s.replaceAll("(bags.|bags|bag.|bag|no other|\\d+)", "").trim())
                        .toList()));

        List<String> list = Arrays.asList("shiny gold");
        List<String> list2 = new ArrayList<>();
        Set<String> output = new HashSet<>();

        while (!list.isEmpty()) {
            for (String s : list) {
                for (Map.Entry<String, List<String>> entry : contain.entrySet()) {
                    if (entry.getValue().contains(s)) {
                        output.add(entry.getKey());
                        list2.add(entry.getKey());
                    }
                }
            }
            list = new ArrayList<>(list2);
            list2.clear();
        }
        System.out.println("Part 1 Output :"+ output.size());

//      *****************************************************************************

        Map<String, Map<String, Integer>> contain2 = inputFile.stream()
                .map(s -> s.split("bags contain"))
                .collect(Collectors.toMap(i -> i[0].trim(), i -> Arrays.asList(i[1].split(","))
                        .stream()
                        .map(s -> s.replaceAll("(bags.|bags|bag.|bag|no other)", "").trim())
                        .filter(s -> !s.isBlank() && !s.isEmpty())
                        .collect(Collectors.toMap(j -> j.substring(j.indexOf(" ")).trim(), j -> Integer.parseInt(j.substring(0, j.indexOf(" ")).trim())))));

        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(List.of(new AbstractMap.SimpleEntry<>("shiny gold", 1)));
        List<Map.Entry<String, Integer>> list4 = new ArrayList<>();
        Integer output2 = 0;

        while (!list3.isEmpty()) {
            for (Map.Entry<String, Integer> stringIntegerEntry : list3) {
                Integer sum = 0;
                Map<String, Integer> key = contain2.get(stringIntegerEntry.getKey());
                for (Map.Entry<String, Integer> entry : key.entrySet()) {
                    list4.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue() * stringIntegerEntry.getValue()));
                    sum = sum + entry.getValue();
                }
                output2 = output2 + (sum * stringIntegerEntry.getValue());
            }
            list3 = new ArrayList<>(list4);
            list4.clear();
        }

        System.out.println("Part 2 Output :"+ output2);
    }
}
