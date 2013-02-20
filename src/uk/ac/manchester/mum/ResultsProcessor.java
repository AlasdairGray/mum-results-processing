package uk.ac.manchester.mum;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class ResultsProcessor {

	private static final String FILE_SEPARATOR = System.getProperty("file.separator");

	private String directoryPath;
	private int experimentSize;

	public ResultsProcessor(String directoryPath, int experimentSize) {
		this.directoryPath = directoryPath;
		this.experimentSize = experimentSize;
	}

	public void aggregateQueries(Collection<QueryResult> experimentResults) throws IOException {
		double[] filterByGraph = new double[7];
		double[] unionByGraph = new double[7];
		double[] perfectUri = new double[7];
		double[] linkedData = new double[7];
		double[] resultSize = new double[7];
		for (QueryResult result : experimentResults) {
			double averageExecutionTime = result.getAverageExecutionTime();
			int queryNumber = result.getQueryNumber();
			switch (result.getExperimentType()) {
			case PERFECT_URIs:
				perfectUri[queryNumber] = perfectUri[queryNumber] + averageExecutionTime;
				break;
			case LINKED_DATA:
				linkedData[queryNumber] = linkedData[queryNumber] + averageExecutionTime;
				break;
			case FILTER_BY_GRAPH:
				filterByGraph[queryNumber] = filterByGraph[queryNumber] + averageExecutionTime;
				resultSize[queryNumber] = result.getAverageNumberResults();
				break;
			case UNION_BY_GRAPH:
				unionByGraph[queryNumber] = unionByGraph[queryNumber] + averageExecutionTime;
				break;
			default:
				break;
			}
		}
		FileWriter writer = new FileWriter(directoryPath + FILE_SEPARATOR + "queriesAggregated.csv");
		writer.append("Query Number, Number of Results, Perfect URIs, Linked Data, Filter by Graph, Union by Graph\n");
		for (int i = 1; i < 7; i++) {
			writer.append(i + ",");
			writer.append(resultSize[i] + ",");
			writer.append(perfectUri[i]/experimentSize + ",");
			writer.append(linkedData[i]/experimentSize + ",");
			writer.append(filterByGraph[i]/experimentSize + ",");
			writer.append(unionByGraph[i]/experimentSize + "\n");
		}
		writer.flush();
		writer.close();
	}

	public void write(
			Collection<QueryResult> experimentResults, String resultsDirectory) throws IOException {
		FileWriter writer = new FileWriter(resultsDirectory + System.getProperty("file.separator") + "results.csv");
		writer.append("ExperimentType, QueryNumber, SeedConcept, NumberOfResults, " +
				"QueryExecutionTime, NumberOfTimeouts\n");
		for (QueryResult result : experimentResults) {
			writer.append(result.toCSV()).append("\n");	
		}
		writer.flush();
		writer.close();
	}

}
