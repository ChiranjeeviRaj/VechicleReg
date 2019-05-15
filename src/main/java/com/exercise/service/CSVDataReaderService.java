package com.exercise.service;

import com.exercise.service.com.exercise.service.model.VehicleModel;

import java.io.IOException;
import java.util.List;

public interface CSVDataReaderService {
    public List<VehicleModel> readCSVFile(String filePath) throws IOException;
}
