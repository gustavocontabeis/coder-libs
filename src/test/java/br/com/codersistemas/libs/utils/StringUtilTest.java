package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testConvertToBoolean() {
		assertTrue(StringUtil.convertToBoolean("S"));
		assertTrue(StringUtil.convertToBoolean("V"));
		assertTrue(StringUtil.convertToBoolean("Y"));
		assertFalse(StringUtil.convertToBoolean("N"));
		assertFalse(StringUtil.convertToBoolean("F"));
	}

	@Test
	public void testCapitalize() {
		assertTrue("Gustavo".equals(StringUtil.capitalize("gustavo")));
		assertTrue("Gustavo da Silva".equals(StringUtil.capitalize("gustavo da Silva")));
	}

	@Test
	public void testUncapitalize() {
		assertTrue("gustavo".equals(StringUtil.uncapitalize("Gustavo")));
		assertTrue("gustavo da Silva".equals(StringUtil.uncapitalize("Gustavo da Silva")));
	}

	@Test
	public void testToCamelCase() {
		fail("implementar...");
//		CaseUtils.toCamelCase(null, false) 
//		assertTrue("gustavo".equals(StringUtil.uncapitalize("Gustavo")));
//		assertTrue("gustavo da Silva".equals(StringUtil.uncapitalize("Gustavo da Silva")));
	}

	@Test
	public void testToUnderlineCase() {
		fail("implementar...");
//		CaseUtils.toCamelCase(null, false) 
//		assertTrue("gustavo".equals(StringUtil.uncapitalize("Gustavo")));
//		assertTrue("gustavo da Silva".equals(StringUtil.uncapitalize("Gustavo da Silva")));
	}

}
