package reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import datamodel.IResult;

public class ResultReporterHTML implements IResultReporter{

	@Override
	public int reportResultInFile(IResult result, String filename) {
		try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
        	
			br.write("<h1 id=\"" + result.getDescription() +  "\">" + result.getDescription() + "</h1>");
			br.newLine();
			
			br.write("<p>Demonstration over (1) Kitchen, (2) Laundry and (3) A/C based in " + result.getAggregateFunction() + " function.</p>");
			br.newLine();
			
			br.write("<ul>");
			br.newLine();
			
			br.write("<h2 id=\"kitchen\">Kitchen</h2>");
			br.newLine();
			br.write("<ul>");
			br.newLine();
			
			for (String s : result.getAggregateMeterKitchen().keySet()) {
			      br.write("<li>" + s + "   " + result.getAggregateMeterKitchen().get(s) + "</li>\n");
			      
			}
			
			br.write("<ul>");
			br.newLine();
			
			br.write("<h2 id=\"laundry\">Laundry</h2>");
			br.newLine();
			br.write("<ul>");
			br.newLine();
			
			for (String s : result.getAggregateMeterLaundry().keySet()) {
			      br.write("<li>" + s + "   " + result.getAggregateMeterLaundry().get(s) + "</li>\n");
			      
			}
			
			br.write("<ul>");
			br.newLine();

			br.write("<h2 id=\"a-c\">A/C</h2>");
			br.newLine();
			br.write("<ul>");
			br.newLine();

			for (String s : result.getAggregateMeterAC().keySet()) {
			      br.write("<li>" + s + "   " + result.getAggregateMeterAC().get(s) + "</li>\n");
			      
			}
	
			br.write("<ul>");
			
			br.close();
			return 0;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

}
