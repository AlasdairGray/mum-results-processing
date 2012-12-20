package uk.ac.manchester.mum;

public class QueryResult {
	private ExperimentType experimentType;
	private int queryNumber;
	private String seedValue;
	private double averageExecutionTime;
	private double averageNumberResults;
	private int timeoutCount;

	public QueryResult(ExperimentType experimentType) {
		this.experimentType = experimentType;
	}

	public ExperimentType getExperimentType() {
		return experimentType;
	}

	public void setExperimentType(ExperimentType experimentType) {
		this.experimentType = experimentType;
	}

	public int getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(int queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getSeedValue() {
		return seedValue;
	}

	public void setSeedValue(String seedValue) {
		this.seedValue = seedValue;
	}

	public double getAverageExecutionTime() {
		return averageExecutionTime;
	}

	public void setAverageExecutionTime(double averageExecutionTime) {
		this.averageExecutionTime = averageExecutionTime;
	}

	public double getAverageNumberResults() {
		return averageNumberResults;
	}

	public void setAverageNumberResults(double averageNumberResults) {
		this.averageNumberResults = averageNumberResults;
	}

	public int getTimeoutCount() {
		return timeoutCount;
	}
	
	public void setTimeoutCount(int timeoutCount) {
		this.timeoutCount = timeoutCount;
	}
	
}
