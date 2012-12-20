package uk.ac.manchester.mum;

import java.util.Map;

public class ProcessResults {

	private String directoryPath;

	public ProcessResults(String string) {
		directoryPath = string;
	}
	
	public void run() throws ResultProcessorException {
		ResultDirectoryProcessor resultDirectoryProcessor = 
				new ResultDirectoryProcessor(directoryPath);
		Map<ExperimentType, Map<Integer, QueryResult>> experimentResults = 
				resultDirectoryProcessor.processDirectory();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Expected directory path for the results directory as an argument.");
			System.exit(1);
		}
		ProcessResults resultsProcessor = new ProcessResults(args[0]);
		try {
			resultsProcessor.run();
		} catch (ResultProcessorException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
