package com.ssd.advent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/6
 */
public class Day6 {
    public static void main(String[] args) throws IOException {
        String inputFile = Files.readString(Path.of("src/main/resources/file6.txt"), StandardCharsets.US_ASCII);

        Scanner input2 = new Scanner(inputFile).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));
        Scanner input3 = new Scanner(inputFile).useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));

        List<String> stringList = new ArrayList<>();
        while (input2.hasNext()) {
            stringList.add(input2.next().trim().replaceAll("\\s+", ""));
        }

        Integer output1 = stringList.stream()
                .map(i -> i.chars().mapToObj(ch -> (char) ch).distinct().toList())
                .map(List::size)
                .reduce(0, Integer::sum);

        System.out.println("Part 1 output :"+ output1);

//      *****************************************************************************

        List<List<String[]>> lists = new ArrayList<>();

        while (input3.hasNext()) {
            List<String[]> chars = new ArrayList<>();
            String[] split = input3.next().trim().split("\\s+");
            for (String s : split) {
                chars.add(String.valueOf(s.toCharArray()).split(""));
            }
            lists.add(chars);
        }

        List<HashSet<String>> output = new ArrayList<>();
        for (List<String[]> list : lists) {
            HashSet<String> set1 = new HashSet<>(Arrays.asList(list.get(0)));
            for (String[] chars : list) {
                if (set1.isEmpty()) {
                    break;
                }
                set1.retainAll(Arrays.asList(chars));
            }
            output.add(set1);
        }

        Integer output2 = output.stream()
                .map(HashSet::size)
                .reduce(0, Integer::sum);

        System.out.println("Part 2 Output :"+ output2);
    }
}
