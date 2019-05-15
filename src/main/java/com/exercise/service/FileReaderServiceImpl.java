package com.exercise.service;

import com.exercise.service.FileReaderService;
import com.exercise.service.com.exercise.service.model.FileInfoModel;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderServiceImpl implements FileReaderService {

    private final static String FILE_CSV = ".csv";
    private final static String FILE_XLS = ".xls";


    public List<FileInfoModel> scanDirectory(String path) throws IOException, MalformedURLException {

        //Reads File on the given directory of the extensions xls/csv
        List<FileInfoModel> fIleInfoModels = new ArrayList<FileInfoModel>();
        fIleInfoModels = Files.list(Paths.get(path))
                .filter(filePath -> filePath.toString().toLowerCase().endsWith(FILE_CSV)
                ||  filePath.toString().toLowerCase().endsWith(FILE_XLS))
                .map(name -> {
                    FileInfoModel model = new FileInfoModel();
                    File file = new File(name.toString());
                    model.setFileName(name.toString());
                    model.setMimeType(getMimeType(file));
                    model.setFileSize(getFileSize(file));
                    model.setExt(getFileExt(file));

                    return model;

                }).collect(Collectors.toList());

        System.out.println(fIleInfoModels);
        return fIleInfoModels;
    }

    private String getMimeType(File file) {
        String type = null;
        try{
        URL u = file.toURI().toURL();
        URLConnection uc = null;
        uc = u.openConnection();
        type = uc.getContentType();
         } catch (IOException e) {
           e.printStackTrace();
        }
        return type;

    }

    private double getFileSize(File file){
        double bytes = file.length();
        return (bytes / 1024);
    }

    private String getFileExt(File file) {
        String extension = "";

            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }

        return extension;

    }
}
