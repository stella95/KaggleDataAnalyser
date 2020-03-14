package dataload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import datamodel.MeasurementRecord;

public class Loader implements ILoader<MeasurementRecord>{
	private String line = "";
	private int sumOfLines=0;
	private MeasurementRecord measRec;
	
	
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, ArrayList<MeasurementRecord> objCollection) {
	
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        	if (hasHeaderLine) {
	        		line = br.readLine();
	        	}
	            while ((line = br.readLine()) != null) {

	                String[] line2 = line.split(delimeter);   
	                if (line2.length==numFields) {
	                	measRec = new MeasurementRecord(line2);
	                	objCollection.add(measRec);
	                	sumOfLines+=1;
	                }
	            }
	 

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return sumOfLines;
	}

}
