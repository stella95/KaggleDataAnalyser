package datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Result implements IResult {
	private HashMap<String, ArrayList<MeasurementRecord>> resultMap = new HashMap<String, ArrayList<MeasurementRecord>>();
	private HashMap<String, ArrayList<MeasurementRecord>> detailedMap = new HashMap<String, ArrayList<MeasurementRecord>>();
	private ArrayList<MeasurementRecord> myArray;
	private int MeasurObjects = 0;
	private String aggFunction;
	private String description;
	private ArrayList<String> sortedKeys;
	private HashMap<String, Double> deviceMap =  new HashMap<String, Double>();
	private DescriptiveStatistics stats;
	
	
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(String aggFunction, String description) {
		this.aggFunction=aggFunction;
		this.description=description;
	}

	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		
		if(resultMap.containsKey(timeUnit)) {
			(resultMap.get(timeUnit)).add(record);
			MeasurObjects+=1;
		}else {
			myArray = new ArrayList<MeasurementRecord>();
			myArray.add(record);
			resultMap.put(timeUnit, myArray);
			MeasurObjects+=1;
		}
		return MeasurObjects;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
		sortedKeys = new ArrayList<String>(resultMap.keySet()); 
		Collections.sort(sortedKeys);
		for (String x : sortedKeys) {
			detailedMap.put(x,resultMap.get(x));
        }
		return detailedMap;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		for(String name: getDetailedResults().keySet()) {
			deviceMap.put(name,aggregateMeter(detailedMap.get(name), 6, aggFunction));
		}
		return deviceMap;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		deviceMap = new HashMap<String, Double>();
		for(String name: detailedMap.keySet()) {
			deviceMap.put(name,aggregateMeter(detailedMap.get(name), 7, aggFunction));
		}
		return deviceMap;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		deviceMap = new HashMap<String, Double>();
		for(String name: detailedMap.keySet()) {
			deviceMap.put(name,aggregateMeter(detailedMap.get(name), 8, aggFunction));
		}
		return deviceMap;
	}

	@Override
	public String getAggregateFunction() {
		return aggFunction;
	}
	
	public double aggregateMeter(ArrayList<MeasurementRecord> array, int device, String aggFunction) {

		if(device==6) {
			stats = new DescriptiveStatistics();
			for(int i = 0; i < array.size(); i++) {
				stats.addValue(Double.parseDouble(array.get(i).getKitchenEnergy()));
			}
		}else if(device==7) {
			stats = new DescriptiveStatistics();
			for(int i = 0; i < array.size(); i++) {
				stats.addValue(Double.parseDouble(array.get(i).getLaundryEnergy()));
			}
		}else {
			stats = new DescriptiveStatistics();
			for(int i = 0; i < array.size(); i++) {
				stats.addValue(Double.parseDouble(array.get(i).getACEnergy()));
			}
		}
		
		
		if(aggFunction=="sum") {
			return stats.getSum();
		}else {
			return (stats.getSum()/stats.getN());
		}
	}

}
