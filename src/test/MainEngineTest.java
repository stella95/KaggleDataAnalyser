package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;


public class MainEngineTest {
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static IResult result;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;
	static private Boolean hasHeader;
	static private int numFields;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();

		inputFilename = "./Resources/TestInput/2007_sample.tsv";
		delimeter = "\t";
		hasHeader = false;
		numFields = 9;
		objCollection = new ArrayList<MeasurementRecord>(); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
	}

	/**
	 * Test method for {@link mainengine.MainEngine#loadData(java.lang.String, java.lang.String, java.lang.Boolean, int, java.util.ArrayList)}.
	 */
	@Test
	public final void testLoadData() {
		int numRows = mainEngine.loadData("./Resources/TestInput/hld_with_emptyCells.txt", ";", true, numFields, objCollection);	
		assertEquals(numRows, 4);
	}

	/**
	 * Test method for {@link mainengine.MainEngine#aggregateByTimeUnit(java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAggregateByTimeUnit() {

		int numRows = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields, objCollection);
		System.out.println("Size to process: " + numRows);
		
		result = mainEngine.aggregateByTimeUnit(objCollection, "dayofweek", "avg", "Day of week avg aggregation over 2007 sample");
		
		//result.reportAggregates();
		assertEquals(result.getAggregateMeterKitchen().get("1"), 1.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("2"), 1.9473684210526316 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("3"), 0.041666666666666664 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("4"), 1.7999999999999998 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("5"), 0.30769230769230765 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("6"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("7"), 0.07142857142857144,2);

		assertEquals(result.getAggregateMeterLaundry().get("1"), 0.5625 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("2"), 0.42105263157894735 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("3"), 2.25 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("4"), 0.25 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("5"), 0.4615384615384615 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("6"), 0.14285714285714288 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("7"), 0.8571428571428571,2);


		assertEquals(result.getAggregateMeterAC().get("1"), 2.1875 ,2);
		assertEquals(result.getAggregateMeterAC().get("2"), 3.7368421052631584 ,2);
		assertEquals(result.getAggregateMeterAC().get("3"), 5.125 ,2);
		assertEquals(result.getAggregateMeterAC().get("4"), 3.8 ,2);
		assertEquals(result.getAggregateMeterAC().get("5"), 6.769230769230769 ,2);
		assertEquals(result.getAggregateMeterAC().get("6"), 9.857142857142858 ,2);
		assertEquals(result.getAggregateMeterAC().get("7"), 4.857142857142857,2);
		
	}

	/**
	 * Test method for {@link mainengine.MainEngine#reportResultInFile(datamodel.IResult, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testReportResultInFile() {
		outputFilename = "./Resources/TestOutput/2007SampleAggregate_Output.txt";
		int numRows = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields, objCollection);
		System.out.println("Size to process: " + numRows);
		
		result = mainEngine.aggregateByTimeUnit(objCollection, "month", "avg", "Monthly avg aggregation over 2007");
		System.out.println("Time units with measurements: " + result.getDetailedResults().size());
		
		int printOutcome = mainEngine.reportResultInFile(result, "md", outputFilename);
		assertEquals(printOutcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename),50);

	}

}
