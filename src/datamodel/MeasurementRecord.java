package datamodel;

public class MeasurementRecord {
	
	private String[] line;
	
	public MeasurementRecord(String[] line) {
		this.line=line;
	}
	
	public String getDate() {
		return line[0];
	}
	
	public String getTime() {
		return line[1];
	}
	
	public String getKitchenEnergy() {
		return line[6];
	}
	
	public String getLaundryEnergy() {
		return line[7];
	}
	
	public String getACEnergy() {
		return line[8];
	}
}
