package org.terrier.structures.indexing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.terrier.indexing.IndexTestUtils;
import org.terrier.structures.Index;
import org.terrier.structures.LexiconEntry;
import org.terrier.tests.ApplicationSetupBasedTest;
import org.terrier.utility.ApplicationSetup;

public class TestIndexing extends ApplicationSetupBasedTest {

	protected Index getIndexBlocks(String[] docnos, String[] docs) throws Exception {
		return IndexTestUtils.makeIndex(docnos, docs);
	}
	
	protected void checkFrequencies(Index index) {
		LexiconEntry le  = null;
		le = index.getLexicon().getLexiconEntry("dramatis");
		assertNotNull(le);
		assertEquals(6, le.getFrequency());
		assertEquals(5, le.getDocumentFrequency());
		
		le = index.getLexicon().getLexiconEntry("personae");
		assertNotNull(le);
		assertEquals(6, le.getFrequency());
		assertEquals(5, le.getDocumentFrequency());
		
		le = index.getLexicon().getLexiconEntry("isnae");
		assertNotNull(le);
		assertEquals(1, le.getFrequency());
		assertEquals(1, le.getDocumentFrequency());
		
	}
	
	String[] DOCNOS_TEST_NIC = new String[]{"doc0", "doc1", "doc2", "doc3", "doc4", "doc5"};
	String[] DOCS_TEST_NIC = new String[]{
			"dramatis", //0
			"DRAMATIS personae", //1
			"personae", //2
			"dramatis isnae personae", //3
			"dramatis dramatis personae personae", //4
			"another term dramatis PeRsOnAe term" //5
			};
	
	@Test public void testNic() throws Exception
	{
		ApplicationSetup.setProperty("termpipelines", "");
		Index index = IndexTestUtils.makeIndex(DOCNOS_TEST_NIC, DOCS_TEST_NIC);
		checkFrequencies(index);		
	}
	
	@Test public void testNicBlocks() throws Exception
	{
		ApplicationSetup.setProperty("termpipelines", "");
		Index index = IndexTestUtils.makeIndexBlocks(DOCNOS_TEST_NIC, DOCS_TEST_NIC);
		checkFrequencies(index);		
	}
	
	@Test public void testNicSp() throws Exception
	{
		ApplicationSetup.setProperty("termpipelines", "");
		Index index = IndexTestUtils.makeIndexSinglePass(DOCNOS_TEST_NIC, DOCS_TEST_NIC);
		checkFrequencies(index);		
	}
	
	
}
