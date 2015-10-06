package br.aniche.ck.metric;

import org.junit.Assert;
import org.junit.Test;

import br.aniche.ck.CK;
import br.aniche.ck.CKNumber;
import br.aniche.ck.CKReport;

public class WMCTest extends BaseTest {

	@Test
	public void should_detect_wmc() {
		
		CKReport report = new CK().calculate(p1dir());

		CKNumber a = report.getByClassName("wmc.CC1");
		Assert.assertEquals(5, a.getWmc());
		CKNumber b = report.getByClassName("wmc.CC2");
		Assert.assertEquals(5, b.getWmc());
	}
}
