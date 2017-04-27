package dynamic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CachedDynamicSuite {
	private static final KnapsackProblem DUMMY_CASE_1 = new KnapsackProblem(6,
	        new int[] { 3, 2, 4, 4 }, new int[] { 4, 3, 2, 3 });

	private static final KnapsackProblem DUMMY_CASE_1_PERMUTED = new KnapsackProblem(
	        6, new int[] { 4, 4, 2, 3 }, new int[] { 3, 2, 3, 4 });

	private static final KnapsackProblem DUMMY_CASE_2 = new KnapsackProblem(10,
	        new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 7, 10 });

	private static final KnapsackProblem DUMMY_CASE_2_PERMUTED = new KnapsackProblem(
	        10, new int[] { 1, 2, 4, 3 }, new int[] { 1, 2, 10, 7 });

	private static final KnapsackProblem TRIVIAL_CASE_EMPTY = new KnapsackProblem(
	        4, new int[] { 10 }, new int[] { 10 });

	private static final KnapsackProblem TRIVIAL_CASE_FULL = new KnapsackProblem(
	        4, new int[] { 4 }, new int[] { 4 });

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private static void testInstance(String caseName, KnapsackProblem problem,
	        boolean[] solution, int value, int weight) {
		AbstractKnapsackSolver solver = new CachedKnapsackSolver(problem);

		solver.solve();

		assertArrayEquals("Solution to " + caseName + " should match",
		        solution, solver.getSolution());

		assertEquals("Solution to " + caseName + " should have correct value",
		        value, solver.getSolutionValue());

		assertEquals("Solution to " + caseName + "should have correct weight",
		        weight, solver.getSolutionWeight());

	}

	@Test
	public void testDummies() {
		testInstance("trivial case full", TRIVIAL_CASE_FULL,
		        new boolean[] { true }, 4, 4);
		testInstance("trivial case empty", TRIVIAL_CASE_EMPTY,
		        new boolean[] { false }, 0, 0);
		testInstance("dummy case 1", DUMMY_CASE_1, new boolean[] { false,
		        false, true, true }, 8, 5);
		testInstance("dummy case 2", DUMMY_CASE_2, new boolean[] { true, true,
		        true, false }, 6, 10);
	}

	@Test
	public void testOnDummiesPermuted() {
		testInstance("dummy case 1 permuted", DUMMY_CASE_1_PERMUTED,
		        new boolean[] { true, true, false, false }, 8, 5);
		testInstance("dummy case 2 permuted", DUMMY_CASE_2_PERMUTED,
		        new boolean[] { true, true, false, true }, 6, 10);
	}

	/* Helper method */

	static final void assertArrayEquals(String message, boolean[] expected,
	        boolean[] real) {
		if (expected.length != real.length) {
			fail(message + " (dimensions don't match)");
		}

		for (int i = 0; i < expected.length; ++i) {
			if (expected[i] != real[i]) {
				fail(message + " expected " + Arrays.toString(expected)
				        + ", but was " + Arrays.toString(real));
			}
		}
	}
}
