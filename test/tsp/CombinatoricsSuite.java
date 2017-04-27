package tsp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CombinatoricsSuite {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTrivial() {
		//subsets of empty sets are empty
		assertTrue(MaskedSets.combinations(0, 0).isEmpty());
		assertTrue(MaskedSets.combinations(0, 10).isEmpty());
		
		//the only zero cardinality is of size 0
		assertTrue(MaskedSets.combinations(10, 0).size() == 1);
	}
	
	@Test
	public void testDummy() {
		assertEquals("the three set case", 3, MaskedSets.combinations(3, 2).size());
		assertEquals("other", 1, MaskedSets.combinations(3, 3).size());
	}

}
