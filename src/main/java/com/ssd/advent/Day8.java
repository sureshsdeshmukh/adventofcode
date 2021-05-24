package com.ssd.advent;

import com.ssd.advent.model.Instruction;
import com.ssd.advent.util.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This code is solution for
 * https://adventofcode.com/2020/day/8
 */
public class Day8 {
    public static void main(String[] args) {
        ArrayList<String> inputFile = FileReader.readFromFile("src/main/resources/file8.txt");

        List<Instruction> instructions = inputFile.stream()
                .map(s -> s.split(" "))
                .map(s -> new Instruction(s[0], Integer.parseInt(s[1]), 0))
                .collect(Collectors.toList());

        int output = 0;

        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.getVisit() == 1) {
                break;
            }
            if (instruction.getOperation().equals("acc")) {
                output = output + instruction.getJump();
            }
            if (instruction.getOperation().equals("jmp")) {
               i = i + instruction.getJump() - 1;
            }
            instruction.setVisit(instruction.getVisit() + 1);
        }
        System.out.println("Part 1 Output :"+ output);

//      *****************************************************************************

        List<Integer> listInt = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).getOperation().equals("jmp") || instructions.get(i).getOperation().equals("nop")) {
                listInt.add(i);
            }
        }

        int counter = 0;
        boolean flag = false;
        Instruction bufferInstruction = null;
        int bufferIndex = 0;

        instructions.forEach(i -> i.setVisit(0));

        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.getVisit() == 1) {
                instructions.forEach(j -> j.setVisit(0));
                if (flag) {
                    String operation = instructions.get(bufferIndex).getOperation().equals("jmp") ? "nop" : "jmp";
                    instructions.get(bufferIndex).setOperation(operation);
                }
                bufferIndex = listInt.get(counter);
                bufferInstruction = instructions.get(bufferIndex);
                String operation = instructions.get(bufferIndex).getOperation().equals("jmp") ? "nop" : "jmp";
                instructions.get(bufferIndex).setOperation(operation);
                flag = true;
                counter++;
                output = 0;
                i = -1; //As continue will increment this to 0
                continue;
            }

            if (instruction.getOperation().equals("acc")) {
                output = output + instruction.getJump();
            }

            if (instruction.getOperation().equals("jmp")) {
                i = i + instruction.getJump() - 1;
            }
            instruction.setVisit(instruction.getVisit() + 1);

            if (instructions.size() -1 == i) {
                System.out.println("Problematic Index :"+ (bufferIndex +1));
                System.out.println("Problematic Instruction :"+ bufferInstruction);
            }
        }
        System.out.println("Part 2 Output :"+ output);
    }
}
