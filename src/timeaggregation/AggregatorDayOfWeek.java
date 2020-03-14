package timeaggregation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class AggregatorDayOfWeek implements IAggregator{
	
	private IResult result ;
	private String unitType;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate date;
	private int day;

	public AggregatorDayOfWeek(String unitType) {
		this.unitType=unitType;
	}

	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		result = new Result(aggFunction, description);
		for (int i = 0; i < inputMeasurements.size(); i++){
			date = LocalDate.parse(inputMeasurements.get(i).getDate(), formatter);
			day = date.getDayOfWeek().getValue();
			result.add(Integer.toString(day),inputMeasurements.get(i));
		}
		return result;
	}

	public String getTimeUnitType() {
		return unitType;
	}

}
