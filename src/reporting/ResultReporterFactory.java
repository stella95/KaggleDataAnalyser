package reporting;

public class ResultReporterFactory {
	public IResultReporter createResultReport(String reportType) {
		switch(reportType) {
		case "text": 
			return new ResultReporterText();
		case "md":
			return new ResultReporterMD();
		case "html":
			return new ResultReporterHTML();

		default: return null;
		}

	}
}
