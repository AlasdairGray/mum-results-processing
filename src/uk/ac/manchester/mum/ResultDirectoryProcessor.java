package uk.ac.manchester.mum;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class ResultDirectoryProcessor {

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private File directory;
	private Collection<QueryResult> experimentResults = 
			new ArrayList<QueryResult>();

	public ResultDirectoryProcessor(String directoryName) {
		directory = new File(directoryName);
		assert(directory.isDirectory());
	}
	
	public Collection<QueryResult> processDirectory() 
			throws ResultProcessorException {
		String[] listing = directory.list();
		for (int i = 0; i < listing.length; i++) {
			String dirName = listing[i];
			System.out.print("Processing directory: " + dirName);
			ExperimentType expType;
			if (dirName.equals("results-fbg_qe")) {
				expType = ExperimentType.FILTER_BY_GRAPH;
			} else if (dirName.equals("results-ld")) {
				expType = ExperimentType.LINKED_DATA;
			} else if (dirName.equals("results-ubg_qe")) {
				expType = ExperimentType.UNION_BY_GRAPH;
			} else if (dirName.equals("results-uri")) {
				expType = ExperimentType.PERFECT_URIs;
			} else {
				System.out.println(" Skip.");
				continue;
			}
			File dir = new File(directory + FILE_SEPARATOR + dirName);
			if (dir.isDirectory()) {
				processSubDirectory(dir, expType);
			}
			System.out.println(" Complete.");
		}
		return experimentResults;
	}

	private void processSubDirectory(File dir, ExperimentType expType) 
			throws ResultProcessorException {
		ResultFileProcessor resultFileProcessor = new ResultFileProcessor();
		String[] listing = dir.list();
		for (int i = 0; i < listing.length; i++) {
			String filename = listing[i];
			experimentResults.addAll( 
					resultFileProcessor.readXML(dir + FILE_SEPARATOR + filename, expType));
		}
	}
	
}
