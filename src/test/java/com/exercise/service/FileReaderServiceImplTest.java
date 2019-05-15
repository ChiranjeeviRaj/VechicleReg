package com.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.exercise.service.com.exercise.service.model.FileInfoModel;

public class FileReaderServiceImplTest {
	
	private FileReaderService service = null;
	private List<FileInfoModel> list = null;	

	@Before
	public void setUp() throws Exception {
		service = new FileReaderServiceImpl();
	}

	@Test
	public void testScanDirectoryValid() throws IOException {
		list = service.scanDirectory("./src/test/resources/data/");
		assertNotNull(list);
		assertEquals(true, !list.isEmpty());
		assertEquals(2, list.size());
	}
	
	@Test(expected = IOException.class)
	public void testScanDirectoryInValid_Path_throw_Exception() throws IOException {
		list = service.scanDirectory("INVALID_PATH");
		assertNotNull(list);
	}
	
	@Test
	public void testScanDirectoryInValid_Empty_dir() throws IOException {
		list = service.scanDirectory("./src/test/resources/EMPTY_DIR/");
		assertNotNull(list);
		assertEquals(true, list.isEmpty());
		assertEquals(0, list.size());
	}
	
	@Test
	public void testScanDirectoryInValid_Filter_CSV() throws IOException {
		list = service.scanDirectory("./src/test/resources/data/");
		assertNotNull(list);
		assertEquals(true, !list.isEmpty());
		assertEquals(2, list.size());
		
		for (FileInfoModel fileInfoModel : list) {
			assertEquals(".csv", fileInfoModel.getExt());
		}
	}


}
