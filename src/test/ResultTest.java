package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataload.ILoader;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class ResultTest {
	
	private ArrayList<MeasurementRecord> objCollection;
	private HashMap<String, ArrayList<MeasurementRecord>> detailedMap;
	private int numFields;
	private ILoader<MeasurementRecord> loader;
	private IResult result;
	DateTimeFormatter formatter ;
	LocalDate date;
	private int month;
	private int objects;
	private HashMap<String, Double> deviceMap;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		loader = new Loader();
		objCollection = new ArrayList<MeasurementRecord>();
		numFields = 9;
		result = new Result("sum", "Test result");
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	@Test
	public void resultTest() {
		
		//sample has only info about January
		loader.load("./Resources/TestInput/testResult_sample.tsv", "\t", false, numFields, objCollection);
		assertEquals(result.getDescription(), "Test result");
		assertEquals(result.getAggregateFunction(), "sum");
		
		for (int i = 0; i < objCollection.size(); i++) {
			date = LocalDate.parse(objCollection.get(i).getDate(), formatter);
			month = date.getMonth().getValue();
			objects = result.add(Integer.toString(month),objCollection.get(i));
		}
		assertEquals(objects, 13);
		
		detailedMap = result.getDetailedResults();
		assertTrue(detailedMap.containsKey("1"));
		
		deviceMap = result.getAggregateMeterLaundry();
		assertEquals(deviceMap.get("1"), 9.0, 2);
		
		deviceMap = result.getAggregateMeterAC();
		assertEquals(deviceMap.get("1"), 104.0, 2);
	}

}
