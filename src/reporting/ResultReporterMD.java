package reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import datamodel.IResult;

public class ResultReporterMD implements IResultReporter{

	@Override
	public int reportResultInFile(IResult result, String filename) {
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
        	
			br.write("# " + result.getDescription());
			br.newLine();
			br.newLine();
			
			br.write("Demonstration over (1) Kitchen, (2) Laundry and (3) A/C based in " + result.getAggregateFunction() + " function.");
			br.newLine();
			br.newLine();
			
			br.write("## Kitchen");
			br.newLine();
			br.newLine();
			
			for (String s : result.getAggregateMeterKitchen().keySet()) {
			      br.write("* " + s + "   " + result.getAggregateMeterKitchen().get(s) + "\n");
			      
			}
			
			br.newLine();
			br.newLine();
			
			br.write("## Laundry");
			br.newLine();
			br.newLine();
			
			for (String s : result.getAggregateMeterLaundry().keySet()) {
			      br.write("* " + s + "   " + result.getAggregateMeterLaundry().get(s) + "\n");
			      
			}
			
			br.newLine();
			br.newLine();
			
			br.write("## A/C");
			br.newLine();
			br.newLine();
			
			for (String s : result.getAggregateMeterAC().keySet()) {
			      br.write("* " + s + "   " + result.getAggregateMeterAC().get(s) + "\n");
			      
			}
			br.close();
			return 0;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

}
