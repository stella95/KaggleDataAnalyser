package timeaggregation;


public class AggregatorFactory {
	
	public IAggregator createAggregator(String unitType) {
		switch(unitType) {
		case "month": 
			return new AggregatorMonth(unitType);
		case "season":
			return new AggregatorSeason(unitType);
		case "dayofweek":
			return new AggregatorDayOfWeek(unitType);
		case "periodofday":
			return new AggregatorPeriodOfDay(unitType);

		default: return null;
		}

	}

}
