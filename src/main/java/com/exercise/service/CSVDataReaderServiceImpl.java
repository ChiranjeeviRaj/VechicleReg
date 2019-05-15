package com.exercise.service;

import com.exercise.service.com.exercise.service.model.VehicleModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVDataReaderServiceImpl implements CSVDataReaderService{

    private final static String COMMA = ",";

    @Override
    public List<VehicleModel> readCSVFile(String filePath) throws IOException {
    	
    	Stream<String> streams = Files.lines(Paths.get(filePath));
    
        List<VehicleModel> vehicleModels = Files.lines(Paths.get(filePath))
        		//.filter(Objects::nonNull)
        		.skip(1)
        		.map( line ->
                {
                    String[] p = line.split(COMMA);// a CSV has comma separated lines
                    VehicleModel item = new VehicleModel();
                    if(p.length == 0){
                    	return item;
                    }
                    item.setVehicleReg(p[0]);
                    item.setMake(p[1]);
                    item.setColor(p[2]);
                    return item;
                }

        )
        		//.ofNullable(list -> list)
        		.collect(Collectors.toList());
        return vehicleModels;
    }

}
