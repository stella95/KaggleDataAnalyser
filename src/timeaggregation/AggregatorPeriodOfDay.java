package timeaggregation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;

public class AggregatorPeriodOfDay implements IAggregator {
	
	private IResult result ;
	private String unitType;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	private LocalTime time;
	private int hour;
	
	public AggregatorPeriodOfDay(String unitType) {
		this.unitType=unitType;
	}

	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		result = new Result(aggFunction, description);
		for (int i = 0; i < inputMeasurements.size(); i++) {
			time = LocalTime.parse(inputMeasurements.get(i).getTime(), formatter);
			hour = Integer.valueOf(time.getHour());
			if((hour>=21) || (hour<5) ) {
				result.add("5:NIGHT",inputMeasurements.get(i));	
			}else if((hour>=5) && (hour<9)) {
				result.add("1:EARLY_MORNING",inputMeasurements.get(i));
			}else if((hour>=9) && (hour<13)) {
				result.add("2:MORNING",inputMeasurements.get(i));
			}else if((hour>=13) && (hour<17)){
				result.add("3:AFTERNOON",inputMeasurements.get(i));
			}else{
				result.add("4:EVENING",inputMeasurements.get(i));
			}			
		}
		return result;
	}

	public String getTimeUnitType() {
		return unitType;
	}

}
