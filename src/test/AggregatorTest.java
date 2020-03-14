package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import timeaggregation.AggregatorDayOfWeek;
import timeaggregation.AggregatorMonth;
import timeaggregation.AggregatorPeriodOfDay;
import timeaggregation.AggregatorSeason;
import timeaggregation.IAggregator;
import dataload.ILoader;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;

public class AggregatorTest {
	private ArrayList<MeasurementRecord> objCollection;
	private int numFields;
	private ILoader<MeasurementRecord> loader;
	private IAggregator agg;
	private IResult result;
	private String inputFilename;
	private String delimeter;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		loader = new Loader();
		numFields = 9;
		inputFilename = "./Resources/TestInput/2007_sample.tsv";
		delimeter = "\t";
		objCollection = new ArrayList<MeasurementRecord>();
	}
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
		agg=null;
	}

	@Test
	public void AggregatorMonthTest() {
		
		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorMonth("month");
		
		assertEquals(agg.getTimeUnitType(), "month");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Monthly average aggregation");
		
		//aggregate result over month
		assertEquals(result.getAggregateMeterKitchen().get("1"), 1.4615384615384615 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("2"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("3"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("4"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("5"), 3.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("6"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("7"), 2.8461538461538463,2);
		assertEquals(result.getAggregateMeterKitchen().get("8"), 0.1111111111111111,2);
		assertEquals(result.getAggregateMeterKitchen().get("9"), 0.125,2);
		assertEquals(result.getAggregateMeterKitchen().get("10"), 0.0,2);
		assertEquals(result.getAggregateMeterKitchen().get("11"), 0.0,2);
		assertEquals(result.getAggregateMeterKitchen().get("12"), 0.09090909090909091,2);

		assertEquals(result.getAggregateMeterLaundry().get("1"), 0.6923076923076923 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("2"), 0.4 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("3"), 0.07692307692307693 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("4"), 0.5555555555555556 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("5"), 0.3333333333333333 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("6"), 0.6363636363636364 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("7"), 0.7692307692307693,2);
		assertEquals(result.getAggregateMeterLaundry().get("8"), 0.3333333333333333,2);
		assertEquals(result.getAggregateMeterLaundry().get("9"), 0.875,2);
		assertEquals(result.getAggregateMeterLaundry().get("10"), 0.5714285714285714,2);
		assertEquals(result.getAggregateMeterLaundry().get("11"), 0.7777777777777778,2);
		assertEquals(result.getAggregateMeterLaundry().get("12"), 3.3636363636363638,2);


		assertEquals(result.getAggregateMeterAC().get("1"), 8.0 ,2);
		assertEquals(result.getAggregateMeterAC().get("2"), 7.2 ,2);
		assertEquals(result.getAggregateMeterAC().get("3"), 5.769230769230769 ,2);
		assertEquals(result.getAggregateMeterAC().get("4"), 1.8888888888888888 ,2);
		assertEquals(result.getAggregateMeterAC().get("5"), 4.166666666666667 ,2);
		assertEquals(result.getAggregateMeterAC().get("6"), 1.5454545454545454 ,2);
		assertEquals(result.getAggregateMeterAC().get("7"), 2.5384615384615383,2);
		assertEquals(result.getAggregateMeterAC().get("8"), 3.7777777777777777,2);
		assertEquals(result.getAggregateMeterAC().get("9"), 2.125,2);
		assertEquals(result.getAggregateMeterAC().get("10"), 5.142857142857143,2);
		assertEquals(result.getAggregateMeterAC().get("11"), 6.0,2);
		assertEquals(result.getAggregateMeterAC().get("12"), 11.454545454545455,2);
		
			
	}
	
	@Test
	public void AggregatorSeasonTest() {
		
		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorSeason("season");
		
		assertEquals(agg.getTimeUnitType(), "season");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Season average aggregation");
		
		//aggregate result over season
		assertEquals(result.getAggregateMeterKitchen().get("SPRING"), 1.0588235294117647 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("AUTUMN"), 0.041666666666666664 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("WINTER"), 0.6896551724137931 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("SUMMER"), 1.1515151515151516 ,2);

		assertEquals(result.getAggregateMeterLaundry().get("SPRING"), 0.29411764705882354 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("AUTUMN"), 0.75 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("WINTER"), 1.6551724137931034 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("SUMMER"), 0.6060606060606061 ,2);
		
		assertEquals(result.getAggregateMeterAC().get("SPRING"), 4.176470588235294 ,2);
		assertEquals(result.getAggregateMeterAC().get("AUTUMN"), 4.458333333333333 ,2);
		assertEquals(result.getAggregateMeterAC().get("WINTER"), 9.172413793103448 ,2);
		assertEquals(result.getAggregateMeterAC().get("SUMMER"), 2.5454545454545454 ,2);
			
	}
	
	@Test
	public void AggregatorDayOfWeekTest() {
		
		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorDayOfWeek("dayofweek");
		
		assertEquals(agg.getTimeUnitType(), "dayofweek");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Day of week average aggregation");
		
		//aggregate result over day of week
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
	
	@Test
	public void AggregatorPeriodOfDayTest() {
		
		int sumOflines = loader.load(inputFilename, delimeter, false, numFields, objCollection);
		System.out.println("Size to process: " + sumOflines);
		agg = new AggregatorPeriodOfDay("periodofday");
		
		assertEquals(agg.getTimeUnitType(), "periodofday");
		result = agg.aggregateByTimeUnit(objCollection,"avg","Period of day average aggregation");
		
		//aggregate result over period of day
		assertEquals(result.getAggregateMeterKitchen().get("1:EARLY_MORNING"), 1.9473684210526316 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("2:MORNING"), 2.25 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("3:AFTERNOON"), 0.0 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("4:EVENING"), 0.13043478260869565 ,2);
		assertEquals(result.getAggregateMeterKitchen().get("5:NIGHT"), 0.4634146341463415 ,2);

		assertEquals(result.getAggregateMeterLaundry().get("1:EARLY_MORNING"), 0.5789473684210527 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("2:MORNING"), 0.4375 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("3:AFTERNOON"), 0.6666666666666666 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("4:EVENING"), 0.5217391304347826 ,2);
		assertEquals(result.getAggregateMeterLaundry().get("5:NIGHT"), 1.2682926829268293 ,2);

		assertEquals(result.getAggregateMeterAC().get("1:EARLY_MORNING"), 5.947368421052632 ,2);
		assertEquals(result.getAggregateMeterAC().get("2:MORNING"), 8.625 ,2);
		assertEquals(result.getAggregateMeterAC().get("3:AFTERNOON"), 2.5238095238095237 ,2);
		assertEquals(result.getAggregateMeterAC().get("4:EVENING"), 4.3478260869565215 ,2);
		assertEquals(result.getAggregateMeterAC().get("5:NIGHT"), 4.7560975609756095 ,2);
			
	}
}
