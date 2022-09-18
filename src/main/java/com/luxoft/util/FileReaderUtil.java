package com.luxoft.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileReaderUtil {
    public static int[][] readCSV(String fileName) {
        List<String> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String row;
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prepareData(rows);
    }

    private static int[][] prepareData(List<String> rows) {
        int rowNumber = 0;
        int[][] elements = new int[9][9];
        for (String row : rows) {
            int columnNumber = 0;
            List<String> seperatedRowElements = Arrays.asList(row.split(","));
            for (String rowElement : seperatedRowElements) {
                if(!rowElement.matches("[1-9]+")){
                    System.out.println("INVALID-INPUT");
                    System.exit(-1);
                }
                elements[rowNumber][columnNumber] = Integer.parseInt(rowElement);
                columnNumber++;
            }
            rowNumber++;
        }
        return elements;
    }
}
