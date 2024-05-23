package com.github.mauricioaniche.ck;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WordCountsTestProduct {
	private CKClassResult w1;
	private CKClassResult w2;

	public void setW1(CKClassResult w1) {
		this.w1 = w1;
	}

	public void setW2(CKClassResult w2) {
		this.w2 = w2;
	}

	public void count() {
		Assertions.assertEquals(1, w1.getMethod("m1/0").get().getUniqueWordsQty());
		Assertions.assertEquals(7, w1.getMethod("m2/0").get().getUniqueWordsQty());
	}

	public void countStaticInitializer() {
		Assertions.assertEquals(1, w2.getMethod("m1/0").get().getUniqueWordsQty());
		Assertions.assertEquals(7, w2.getMethod("m2/0").get().getUniqueWordsQty());
		Assertions.assertEquals(3, w2.getMethod("(initializer 1)").get().getUniqueWordsQty());
	}

	public void countAtClassLevel() {
		Assertions.assertEquals(10, w1.getUniqueWordsQty());
		Assertions.assertEquals(13, w2.getUniqueWordsQty());
	}
}