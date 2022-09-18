package com.luxoft.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ValidationServiceImplTest {
    private int[][] validSudoku =
            {{4, 3, 5, 2, 6, 9, 7, 8, 1},
            {6, 8, 2, 5, 7, 1, 4, 9, 3},
            {1, 9, 7, 8, 3, 4, 5, 6, 2},
            {8, 2, 6, 1, 9, 5, 3, 4, 7},
            {3, 7, 4, 6, 8, 2, 9, 1, 5},
            {9, 5, 1, 7, 4, 3, 6, 2, 8},
            {5, 1, 9, 3, 2, 6, 8, 7, 4},
            {2, 4, 8, 9, 5, 7, 1, 3, 6},
            {7, 6, 3, 4, 1, 8, 2, 5, 9}};
    private ValidationService validationService = new ValidationServiceImpl();

    @Test
    public void testValidateRow_should_valid_when_sudoku_is_valid() {
        List<String> rowChecks = validationService.validateRow(validSudoku);
        Assert.assertEquals(0,rowChecks.size());
    }

    @Test
    public void testValidateRow_should_invalid_when_sudoku_is_invalid() {
        validSudoku[0][0] = 1;
        List<String> rowChecks = validationService.validateRow(validSudoku);
        Assert.assertEquals(1,rowChecks.size());
        Assert.assertEquals("row : 1 is invalid",rowChecks.get(0));
    }

    @Test
    public void testValidateColumn_should_valid_when_sudoku_is_valid() {
        List<String> columnChecks = validationService.validateColumn(validSudoku);
        Assert.assertEquals(0,columnChecks.size());
    }

    @Test
    public void testValidateColumn_should_invalid_when_sudoku_is_invalid() {
        validSudoku[0][0] = 1;
        List<String> columnChecks = validationService.validateColumn(validSudoku);
        Assert.assertEquals(1,columnChecks.size());
        Assert.assertEquals("column : 1 is invalid",columnChecks.get(0));
    }

    @Test
    public void testValidateSector_should_valid_when_sudoku_is_valid() {
        List<String> sectorsChecks = validationService.validateSector(validSudoku);
        Assert.assertEquals(0,sectorsChecks.size());
    }

    @Test
    public void tesValidateSector_should_invalid_when_sudoku_is_invalid() {
        validSudoku[0][1] = 1;
        validSudoku[8][8] = 1;
        List<String> sectorsChecks = validationService.validateSector(validSudoku);
        Assert.assertEquals(2,sectorsChecks.size());
        Assert.assertEquals("sector : 1 - 1 is invalid",sectorsChecks.get(0));
        Assert.assertEquals("sector : 3 - 3 is invalid",sectorsChecks.get(1));
    }
}