package timeaggregation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class AggregatorMonth implements IAggregator{
	private IResult result ;
	private String unitType;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate date;
	private int month;

	public AggregatorMonth(String unitType) {
		this.unitType=unitType;
	}

	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		result = new Result(aggFunction, description);
		for (int i = 0; i < inputMeasurements.size(); i++) {
			date = LocalDate.parse(inputMeasurements.get(i).getDate(), formatter);
			//getMonth Gets the month of year, getValue pernei ton mina se arithmo se int kai meta to metatrepei se string
			month = date.getMonth().getValue();
			result.add(Integer.toString(month),inputMeasurements.get(i));
		}
		return result;
	}

	public String getTimeUnitType() {
		return unitType;
	}
	
	

}
