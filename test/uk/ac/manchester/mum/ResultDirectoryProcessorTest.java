package uk.ac.manchester.mum;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

public class ResultDirectoryProcessorTest {

	@Test
	public void testProcessDirectory() throws ResultProcessorException {
		ResultDirectoryProcessor resultDirectoryProcessor = new ResultDirectoryProcessor("test-data");
		Collection<QueryResult> experimentResults = 
				resultDirectoryProcessor.processDirectory();
		assertEquals(24, experimentResults.size());
	}

}
