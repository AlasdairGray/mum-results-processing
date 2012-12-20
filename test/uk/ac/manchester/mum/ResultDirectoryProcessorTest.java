package uk.ac.manchester.mum;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class ResultDirectoryProcessorTest {

	@Test
	public void testProcessDirectory() throws ResultProcessorException {
		ResultDirectoryProcessor resultDirectoryProcessor = new ResultDirectoryProcessor("test-data");
		Map<ExperimentType, Map<Integer, QueryResult>> experimentResults = 
				resultDirectoryProcessor.processDirectory();
		assertEquals(4, experimentResults.size());
	}

}
