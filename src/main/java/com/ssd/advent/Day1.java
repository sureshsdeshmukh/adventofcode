package com.ssd.advent;

import com.ssd.advent.util.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/1
 */
public class Day1 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file1.txt");

        List<Integer> integerList = inputFile.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Integer output1 = integerList.stream()
                .filter(i -> integerList.contains(2020 - i))
                .peek(System.out::println)
                .reduce(1, (i, j) -> i * j);

        System.out.println("Part 1 Output :"+ output1);

//      *****************************************************************************

        for (int i = 0; i < integerList.size() - 2; i++) {
            int val = 2020 - integerList.get(i);
            Set<Integer> set2 = new HashSet<>();
            for (int j = i + 1; j < integerList.size(); j++) {
                int val2 = val - integerList.get(j);
                if (set2.contains(val2)) {
                    System.out.println("Values are :"+ (integerList.get(i) +" : "+ integerList.get(j) +" : "+ val2));
                    System.out.println("Part 2 output :"+ (integerList.get(i) * integerList.get(j) * val2));
                }
                set2.add(integerList.get(j));
            }
        }
    }
}
