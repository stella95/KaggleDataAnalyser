package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({MainEngineTest.class, LoaderTest.class,AggregatorTest.class, ResultReporterTest.class, ResultTest.class})
public class AllTests{
	//The above directives simply run all tests
}
