package com.exercise.service;

import com.exercise.service.com.exercise.service.model.FileInfoModel;

import java.io.IOException;
import java.util.List;

public interface FileReaderService {


    public List<FileInfoModel> scanDirectory(String path) throws IOException;

}
