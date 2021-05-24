package com.ssd.advent;

import com.ssd.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/5
 */
public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file5.txt");

        Map<String, Integer> val = inputFile.stream()
                .map(String::strip)
                .collect(Collectors.toMap(b -> b, b -> getRow(127, b.toCharArray(), 0, b.toCharArray().length - 3)
                        * 8
                        + getRow(7, b.toCharArray(), b.toCharArray().length - 3, b.toCharArray().length)));

        Map.Entry<String, Integer> stringIntegerEntry = val.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("Part 1 Output :" + stringIntegerEntry.getValue());

//      *****************************************************************************

        int[] vals = val.values().stream().sorted().mapToInt(value -> value).toArray();
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] + 1 != vals[i + 1]) {
                System.out.println("Part 2 Output : "+ (vals[i] + 1));
                break;
            }
        }
    }

    static int getRow(int high, char[] pass, int start, int end) {
        int low = 0;
        int med;

        for (int i = start; i < end; i++) {
            med = (high - low) / 2;
            if (pass[i] == 'F' || pass[i] == 'L') {
                high = med + low;
            }
            if (pass[i] == 'B' || pass[i] == 'R') {
                low = med + low + 1;
            }
        }
        return high;
    }
}
