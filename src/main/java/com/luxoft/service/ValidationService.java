package com.luxoft.service;

import java.util.List;

public interface ValidationService {
    List<String> validateRow(int[][] elements);
    List<String> validateColumn(int[][] elements);
    List<String> validateSector(int[][] elements);
}
