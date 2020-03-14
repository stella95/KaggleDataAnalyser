package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataload.ILoader;
import dataload.Loader;
import datamodel.MeasurementRecord;

public class LoaderTest {
	
	private ArrayList<MeasurementRecord> objCollection;
	private int numFields;
	private ILoader<MeasurementRecord> loader;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Nothing todo here.
	}

	@Before
	public void setUp() throws Exception {
		loader = new Loader();
		objCollection = new ArrayList<MeasurementRecord>();
		numFields = 9;
	}

	@Test
	public void testLoader() {
		int sumOflines = loader.load("./Resources/TestInput/hld_with_emptyCells.txt", ";", true, numFields, objCollection);
		assertEquals(sumOflines, 4);
	}

}
