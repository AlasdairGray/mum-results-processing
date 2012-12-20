package uk.ac.manchester.mum;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class ResultFileProcessorTest {

	@Test(expected=ResultProcessorException.class)
	public void fileNotFound() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		processor.readXML("something", ExperimentType.PERFECT_URIs);
	}
	
	@Test
	public void processFBGResultsFile() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-fbg_qe/2012.12.10.182654513_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		assertEquals(6, results.size());
	}
	
	@Test
	public void processLDResultsFile() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-ld/2012.12.10.18110580_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		assertEquals(6, results.size());
	}
	
	@Test
	public void processUBGResultsFile() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-ubg_qe/2012.12.10.183554417_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		assertEquals(6, results.size());
	}
	
	@Test
	public void processURIResultsFile() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-uri/2012.12.10.180217462_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		assertEquals(6, results.size());
	}
	
	@Test
	public void processQueryOne() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-fbg_qe/2012.12.10.182654513_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		QueryResult queryResult = results.get(new Integer("1"));
		assertEquals(1, queryResult.getQueryNumber());
		assertEquals(ExperimentType.FILTER_BY_GRAPH, queryResult.getExperimentType());
		assertEquals("http://www.conceptwiki.org/concept/ef9c1204-c447-4322-9432-ded0ef79f6b9", queryResult.getSeedValue());
		assertEquals(0.026931, queryResult.getAverageExecutionTime(), 0);
		assertEquals(1.00, queryResult.getAverageNumberResults(), 0);
		assertEquals(0, queryResult.getTimeoutCount());
	}

	@Test
	public void timeoutCount() throws ResultProcessorException {
		ResultFileProcessor processor = new ResultFileProcessor();
		Map<Integer, QueryResult> results = processor.readXML(
				"test-data/results-ubg_qe/2012.12.10.183554417_bmresult.xml",
				ExperimentType.FILTER_BY_GRAPH);
		QueryResult queryResult = results.get(new Integer("6"));
		assertEquals(50, queryResult.getTimeoutCount());
	}

}
