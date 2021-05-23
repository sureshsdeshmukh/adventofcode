package com.ssd.advent;

import com.ssd.advent.util.FileReader;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/2
 */
public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file2.txt");

        List<String> stringsList = inputFile.stream()
                .map(String::strip)
                .flatMap(i -> Arrays.stream(i.split(" ")))
                .flatMap(i -> Arrays.stream(i.split("-")))
                .flatMap(i -> Arrays.stream(i.split(":")))
                .map(String::strip)
                .collect(Collectors.toList());

        List<Policy> policyList = new ArrayList<>();
        for (int i = 0; i < stringsList.size(); i++) {
            policyList.add(new Policy(Integer.parseInt(stringsList.get(i)), Integer.parseInt(stringsList.get(++i)),
                    stringsList.get(++i).charAt(0), stringsList.get(++i).toCharArray()));
        }

        List<Policy> collect1 = policyList.stream()
                .filter(i -> {
                    int count = (int) CharBuffer.wrap(i.input()).chars()
                            .mapToObj(ch -> (char) ch)
                            .filter(k -> k == i.allowedChar())
                            .count();
                    return count >= i.min() && count <= i.max();
                })
                .collect(Collectors.toList());

        System.out.println("Part 1 Output :"+ (long) collect1.size());

//      *****************************************************************************

        List<Policy> collect2 = policyList.stream()
                .filter(i -> (i.input()[i.min() - 1] == i.allowedChar() ^ (i.input()[i.max() - 1] == i.allowedChar())))
                .collect(Collectors.toList());

        System.out.println("Part 2 Output :"+ (long) collect2.size());

    }

    record Policy(int min, int max, char allowedChar, char[] input) {
    }
}
