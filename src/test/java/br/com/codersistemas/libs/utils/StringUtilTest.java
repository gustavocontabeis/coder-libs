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
		assertTrue("gustavoDaSilva".equals(StringUtil.toCamelCase("gustavo da silva")));
		assertTrue("gustavoDaSilva".equals(StringUtil.toCamelCase("gustavo_da_silva")));
	}

	@Test
	public void testToUnderlineCase() {
		assertTrue("gustavo_da_silva".equals(StringUtil.toUnderlineCase("gustavoDaSilva")));
		assertTrue("Gustavo_da_silva".equals(StringUtil.toUnderlineCase("GustavoDaSilva")));
	}

	@Test
	public void testLabel() {
		assertTrue("Nome".equals(StringUtil.label("nome")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("gustavo_da_silva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("gustavoDaDilva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("GustavoDaDilva")));
	}

}
