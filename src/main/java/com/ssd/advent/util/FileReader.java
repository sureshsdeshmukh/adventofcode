package com.ssd.advent.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public static ArrayList<String> readFromFile(String file){
        ArrayList<String> lines = new ArrayList<>();
//      We create a scanner for reading the file
        try(Scanner scanner = new Scanner(Path.of(file))){
//          We read all the lines of the file
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
