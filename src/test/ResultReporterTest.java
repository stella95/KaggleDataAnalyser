package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataload.ILoader;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import reporting.IResultReporter;
import reporting.ResultReporterHTML;
import reporting.ResultReporterMD;
import reporting.ResultReporterText;
import timeaggregation.AggregatorSeason;
import timeaggregation.IAggregator;

public class ResultReporterTest {
	
	private ArrayList<MeasurementRecord> objCollection;
	private int numFields;
	private ILoader<MeasurementRecord> loader;
	private IAggregator agg;
	private IResult result;
	private IResultReporter reporter;
	private String inputFilename;
	private String outputFilename;
	private String delimeter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		//reporter = new ResultReporterText();
		//result = new Result();
		loader = new Loader();
		numFields = 9;
		inputFilename = "./Resources/TestInput/2007_sample.tsv";
		delimeter = "\t";
		objCollection = new ArrayList<MeasurementRecord>();
		
		outputFilename = "./Resources/TestOutput/reporttest.txt";
	}
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
		agg=null;
		reporter=null;
	}

	@Test
	public void testResultReporterText() {
		
		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorSeason("season");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Season average aggregation");
		
		reporter = new ResultReporterText();
		int outcome = reporter.reportResultInFile(result, outputFilename);
		assertEquals(outcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename),17);
		
	}
	
	@Test
	public void testResultReporterMD() {

		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorSeason("season");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Season average aggregation");
		
		reporter = new ResultReporterMD();
		int outcome = reporter.reportResultInFile(result, outputFilename);
		assertEquals(outcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename),26);
				
	}
	
	@Test
	public void testResultReporterHTML() {

		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorSeason("season");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Season average aggregation");
		
		reporter = new ResultReporterHTML();
		int outcome = reporter.reportResultInFile(result, outputFilename);
		assertEquals(outcome, 0);
		assertEquals(FileUtilities.countLinesOfAFile(outputFilename),24);
				
	}
}
