	package timeaggregation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class AggregatorSeason implements IAggregator {
	
	private IResult result ;
	private String unitType;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate date;
	private HashMap<String,String> seasonMap = new HashMap<String,String>();

	public AggregatorSeason(String unitType) {
		this.unitType=unitType;
		seasonMap.put("DECEMBER", "WINTER");
		seasonMap.put("JANUARY", "WINTER");
		seasonMap.put("FEBRUARY", "WINTER");
		seasonMap.put("MARCH", "SPRING");
		seasonMap.put("APRIL", "SPRING");
		seasonMap.put("MAY", "SPRING");
		seasonMap.put("JUNE", "SUMMER");
		seasonMap.put("JULY", "SUMMER");
		seasonMap.put("AUGUST", "SUMMER");
		seasonMap.put("SEPTEMBER", "AUTUMN");
		seasonMap.put("OCTOBER", "AUTUMN");
		seasonMap.put("NOVEMBER", "AUTUMN");
	}

	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		result = new Result(aggFunction, description);
		for (int i = 0; i < inputMeasurements.size(); i++){
			date = LocalDate.parse(inputMeasurements.get(i).getDate(), formatter);
			result.add(seasonMap.get(date.getMonth().toString()),inputMeasurements.get(i));
		}
		return result;
	}

	public String getTimeUnitType() {
		return unitType;
	}

}
