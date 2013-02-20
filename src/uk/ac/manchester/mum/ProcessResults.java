package uk.ac.manchester.mum;

import java.io.IOException;
import java.util.Collection;

public class ProcessResults {

	private String directoryPath;
	private int experimentSize;

	public ProcessResults(String directoryname, int experimentSize) {
		directoryPath = directoryname;
		this.experimentSize = experimentSize;
	}
	
	public void run() throws ResultProcessorException, IOException {
		System.out.println("Reading MUMB results from " + directoryPath);
		ResultDirectoryProcessor resultDirectoryProcessor = 
				new ResultDirectoryProcessor(directoryPath);
		Collection<QueryResult> experimentResults = 
				resultDirectoryProcessor.processDirectory();
		System.out.println("Processing query results");
		ResultsProcessor resultWriter = new ResultsProcessor(directoryPath, experimentSize);
		resultWriter.aggregateQueries(experimentResults);
		System.out.println("Writing data files to " + directoryPath);
		resultWriter.write(experimentResults, directoryPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Two parameters are required:");
			System.err.println("\tDirectory path for the results directory;");
			System.err.println("Number of experiment iterations.");
			System.exit(1);
		}
		ProcessResults resultsProcessor = new ProcessResults(args[0], new Integer(args[1]).intValue());
		try {
			resultsProcessor.run();
			System.out.println("Success!");
		} catch (ResultProcessorException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
