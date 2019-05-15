package com.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.exercise.service.com.exercise.service.model.VehicleModel;

public class CSVDataReaderServiceImplTest {
	
	private CSVDataReaderService service = null;
	private List<VehicleModel> list = null;

	@Before
	public void setUp() throws Exception {
		service = new CSVDataReaderServiceImpl();
	}

	@Test(expected = IOException.class)
	public void testReadCSVFileWithInValidFilePathThowException() throws IOException {
		list = service.readCSVFile("INVALID_PATH");
	}
	
	@Test()
	public void testReadCSVFileWithInValid_EMPTY_FILE() throws IOException {
		list = service.readCSVFile("./src/test/resources/data/TEST_IN_VALID.csv");
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test()
	public void testReadCSVFileWithValid() throws IOException {
		list = service.readCSVFile("./src/test/resources/data/TEST_VALID.csv");
		assertNotNull(list);
	}
	
	@Test()
	public void testReadCSVFileWithValid_List_3() throws IOException {
		list = service.readCSVFile("./src/test/resources/data/TEST_VALID.csv");
		assertNotNull(list);
		assertEquals(3, list.size());
	}

}
