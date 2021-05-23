package com.ssd.advent;

import com.ssd.advent.util.FileReader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/3
 */
public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file3.txt");

        List<char[]> collect = inputFile.stream()
                .map(String::strip)
                .map(String::toCharArray)
                .collect(Collectors.toList());

        List<Rule> rules1 = new ArrayList<>();
        rules1.add(new Rule(3, 1));

        long output1 = numberOfTrees(collect, rules1);
        System.out.println("Part 1 Output :" +output1);

//      *****************************************************************************

        List<Rule> rules2 = new ArrayList<>();
        rules2.add(new Rule(1, 1));
        rules2.add(new Rule(3, 1));
        rules2.add(new Rule(5, 1));
        rules2.add(new Rule(7, 1));
        rules2.add(new Rule(1, 2));

        long output2 = numberOfTrees(collect, rules2);
        System.out.println("Part 2 OUtput :"+ output2);
    }

    private static long numberOfTrees(List<char[]> collect, List<Rule> rules) {
        return rules.stream()
                .map(i -> getCounter(collect, i.right(), i.down()))
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply)
                .longValue();
    }

    private static int getCounter(List<char[]> collect, int right, int down){
        int counter = 0;
        int j = 0;
        for (int i = 0; i < collect.size(); i = i + down) {
            char[] line = collect.get(i);

            if(line[j] == '#') {
                counter++;
            }
            j = j + right;
            if (j >= line.length) {
                j = j - line.length;
            }
        }
        return counter;
    }

    record Rule(int right, int down) {
    }
}
