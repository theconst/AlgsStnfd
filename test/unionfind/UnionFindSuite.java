package unionfind;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSuite {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindOnInit() {
		int size = 10;
		UnionFind uf = new UnionFind(size);
		
		for (int i = 0; i < size; i++) {
			assertTrue(uf.find(i) == i);
		}
	}
	
	@Test
	public void testOneUnion() {
		int size = 10;
		UnionFind uf = new UnionFind(size);
		
		uf.union(1, 9);
		
		assertTrue(uf.find(1) == uf.find(9));
		assertTrue(uf.find(9) == uf.find(1));
		assertTrue(uf.rank(1) == 1);
		assertEquals("classes decreased", size - 1, uf.classes());
	}
	
	@Test
	public void testUnionsTransitivity() {
		int size = 10;
		UnionFind uf = new UnionFind(size);
		
		uf.union(0, 1);
		assertEquals("classes decreased", size - 1, uf.classes());
		uf.union(1, 2);
		assertEquals("classes decreased", size - 2, uf.classes());
		uf.union(2, 9);
		assertEquals("classes decreased", size - 3, uf.classes());
		uf.union(2, 3);
		assertEquals("classes decreased", size - 4, uf.classes());
		uf.union(3, 9);
		
		//test invariants
		assertTrue(uf.find(9) == uf.find(0));
		assertTrue(uf.find(2) == uf.find(9));
		assertTrue(uf.find(0) == uf.find(2));
		assertTrue(uf.find(9) == uf.find(0));
		assertTrue(uf.find(1) == uf.find(3));
		assertTrue(uf.find(9) == uf.find(0));
		assertTrue(uf.find(1) == uf.find(0));
		assertTrue(uf.find(3) == uf.find(2));
		assertEquals("rank of the root", 1, uf.rank(0));
		assertEquals("rank of leaf", 0, uf.rank(2));
		assertEquals("rank of leaf", 0, uf.rank(3));
		assertEquals("rank of leaf", 0, uf.rank(9));
		
		assertEquals("classes not changed", size - 4, uf.classes());
	}
	
	@Test
	public void testRank() {
		UnionFind uf = new UnionFind(8);
		
		uf.union(0, 1);
		uf.union(2, 3);
		uf.union(4, 5);
		uf.union(6, 7);
		
		uf.union(0, 2);
		uf.union(4, 6);
		uf.union(0, 6);
		
		assertEquals("rank of root", 3, uf.rank(0));
		assertEquals("rank of 2", 2, uf.rank(4));
		assertEquals("rank of 1", 1, uf.rank(6));
		assertEquals("rank of 1", 1, uf.rank(2));
		assertEquals("rank of 0", 0, uf.rank(1));
		assertEquals("rank of 0", 0, uf.rank(3));
		assertEquals("rank of 0", 0, uf.rank(5));
		assertEquals("rank of 0", 0, uf.rank(7));
		assertEquals("classes is correct", 1, uf.classes());
	}
	
	
	@Test
	public void testCompletion() {
		UnionFind uf = new UnionFind(8);
		
		uf.union(0, 1);
		uf.union(2, 3);
		uf.union(4, 5);
		uf.union(6, 7);
		
		uf.union(0, 2);
		uf.union(4, 6);
		uf.union(0, 6);
		
		uf.find(7);
		
		for (int q : new int[] {4, 6, 7}) {
			assertEquals("depth on the path should be one - " + 
				uf.toString(), 1, uf.depth(q));
		}
		
		assertEquals("depth of the root should be zero -" +
				uf.toString(), 0, uf.depth(0));
	}

}
