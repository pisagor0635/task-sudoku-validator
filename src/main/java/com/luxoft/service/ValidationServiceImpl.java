package com.luxoft.service;

import com.luxoft.util.FileReaderUtil;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    public ValidationServiceImpl(String fileName) {
        int[][] elements = FileReaderUtil.readCSV(fileName);
        startValidation(elements);
    }
    @Override
    public List<String> validateRow(int[][] elements) {
        List<String> rowChecks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Set<Integer> temp = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                temp.add(elements[i][j]);
            }
            if (temp.size() != 9) {
                rowChecks.add("row : " + (i + 1) + " is invalid");
            }
        }
        return rowChecks;
    }

    @Override
    public List<String> validateColumn(int[][] elements) {
        List<String> columnChecks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Set<Integer> temp = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                temp.add(elements[j][i]);
            }
            if (temp.size() != 9) {
                columnChecks.add("column : " + (i + 1) + " is invalid");
            }
        }
        return columnChecks;
    }

    @Override
    public List<String> validateSector(int[][] elements) {
        List<String> sectorChecks = new ArrayList<>();
        int rowSubHelper = 0;
        int columnSubHelper = 0;
        do {
            Set<Integer> temp = new HashSet<>();
            for (int i = 3 * rowSubHelper; i < 3 * rowSubHelper + 3; i++) {
                for (int j = 3 * columnSubHelper; j < 3 * columnSubHelper + 3; j++) {
                    temp.add(elements[j][i]);
                }
            }
            if (temp.size() != 9) {
                sectorChecks.add("sector : " + (columnSubHelper + 1) + " - " + (rowSubHelper + 1) + " is invalid");
            }
            columnSubHelper++;
            if (columnSubHelper == 3) {
                rowSubHelper++;
                columnSubHelper = 0;
            }
        } while (rowSubHelper != 3);
        return sectorChecks;
    }

    private void startValidation(int[][] elements) {
        List<String> validationResults = new ArrayList<>();
        validationResults.addAll(validateRow(elements));
        validationResults.addAll(validateColumn(elements));
        validationResults.addAll(validateSector(elements));

        finalOutput(validationResults);
    }

    private int finalOutput(List<String> validationResults) {

        if (validationResults.isEmpty()) {
            System.out.println("****** VALID ******");
            return 0;
        } else {
            System.out.println("****** INVALID ******");
            validationResults.forEach(System.out::println);
            return -1;
        }
    }
}
