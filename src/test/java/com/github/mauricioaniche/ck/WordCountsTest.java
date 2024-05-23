package com.github.mauricioaniche.ck;

import org.junit.jupiter.api.*;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordCountsTest extends BaseTest {

	private WordCountsTestProduct wordCountsTestProduct = new WordCountsTestProduct();
	private CKClassResult w3;

	@BeforeAll
	public void setUp() {
		report = run(fixturesDir() + "/wordcounts");
	}
	
	@BeforeEach
	public void getClasses() {
		wordCountsTestProduct.setW1(report.get("wordcounts.WordCounts"));
		wordCountsTestProduct.setW2(report.get("wordcounts.WordCounts2"));
		this.w3 = report.get("wordcounts.WordCounts3");
	}

	@Test
	public void count() {
		wordCountsTestProduct.count();
	}

	// related to issue #33
	@Test
	public void countStaticInitializer() {
		wordCountsTestProduct.countStaticInitializer();
	}

	@Test
	public void countAtClassLevel() {
		wordCountsTestProduct.countAtClassLevel();
	}

	// related to issue #34
	@Test
	public void subclasses() {
		Assertions.assertEquals(7, w3.getMethod("m2/0").get().getUniqueWordsQty());
		Assertions.assertEquals(10, w3.getUniqueWordsQty());

		// numbers in the subclass
		CKClassResult subclass = report.get("wordcounts.WordCounts3$1X");
		Assertions.assertEquals(3, subclass.getMethod("xxx/0").get().getUniqueWordsQty());
		Assertions.assertEquals(4, subclass.getUniqueWordsQty());

		CKClassResult subclass2 = report.get("wordcounts.WordCounts3$Y");
		Assertions.assertEquals(2, subclass2.getMethod("yyy/0").get().getUniqueWordsQty());
		Assertions.assertEquals(3, subclass2.getUniqueWordsQty());
	}
}
