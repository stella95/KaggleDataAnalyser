package mainengine;

import java.util.ArrayList;

import dataload.ILoader;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import reporting.IResultReporter;
import reporting.ResultReporterFactory;
import timeaggregation.AggregatorFactory;
import timeaggregation.IAggregator;

public class MainEngine implements IMainEngine {
	private IResult result;
	private ILoader<MeasurementRecord> loader = new Loader();
	private IAggregator aggr;
	private IResultReporter reporter;
	private ResultReporterFactory reporterFactory = new ResultReporterFactory();
	private AggregatorFactory aggFactory = new AggregatorFactory();
	
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields,
			ArrayList<MeasurementRecord> objCollection) {
			int sumOfLines = loader.load(fileName, delimeter, hasHeaderLine, numFields, objCollection);
		return sumOfLines;
	}

	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType,
			String aggFunction, String description) {
		aggr = aggFactory.createAggregator(aggregatorType);
		result = aggr.aggregateByTimeUnit(inputMeasurements, aggFunction, description);
		return result;
	}


	public int reportResultInFile(IResult result, String reportType, String filename) {
		reporter = reporterFactory.createResultReport(reportType);
		int taskComp = reporter.reportResultInFile(result, filename);
		return taskComp;
	}

}
