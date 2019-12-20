package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
		assertTrue("gustavo_Da_Silva".equals(StringUtil.toUnderlineCase("gustavoDaSilva")));
		assertTrue("Gustavo_Da_Silva".equals(StringUtil.toUnderlineCase("GustavoDaSilva")));
	}

	@Test
	public void testLabel() {
		assertTrue("Nome".equals(StringUtil.label("nome")));
		assertTrue("Gustavo da silva".equals(StringUtil.label("gustavo_da_silva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("gustavoDaSilva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("GustavoDaSilva")));
		assertTrue("Data de Nascimento".equals(StringUtil.label("dataDeNascimento")));
		assertTrue("Descrição".equals(StringUtil.label("descricao")));
		assertTrue("Descrições".equals(StringUtil.label("descricoes")));
	}
	
	
	@Test
	public void testFindTag() {
		
		StringBuilder sb = new StringBuilder();
		String input = "<font size=\"5\"><p>some text</p>\n<p>another text</p></font>";
//		//String regex = "</?(font|p){1}.*?/?>";
//		String regex = "<p>*.?</p>";
//		String stripped = input.replaceAll(regex, "");
//		//System.out.println(stripped);
//		System.out.println("-------------");
//		Matcher matcher = Pattern.compile(regex).matcher(input);
//		while (matcher.find()) {
//			String group = matcher.group();
//			System.out.println(group);
//			
//		}
		
		String form = 
				"<form>" +
				"  <div class=\"form-group\">" +
				"    <label for=\"exampleInputEmail1\">Email address</label>" +
				"    <input type=\"email\" class=\"form-control\" id=\"exampleInputEmail1\" aria-describedby=\"emailHelp\" placeholder=\"Enter email\">" +
				"    <small id=\"emailHelp\" class=\"form-text text-muted\">We'll never share your email with anyone else.</small>" +
				"  </div>" +
				"  <div class=\"form-group\">" +
				"    <label for=\"exampleInputPassword1\">Password</label>" +
				"    <input type=\"password\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"Password\">" +
				"  </div>" +
				"  <div class=\"form-check\">" +
				"    <input type=\"checkbox\" class=\"form-check-input\" id=\"exampleCheck1\">" +
				"    <label class=\"form-check-label\" for=\"exampleCheck1\">Check me out</label>" +
				"  </div>" +
				"  <button type=\"submit\" class=\"btn btn-primary\">Submit</button>" +
				"</form>";
		
		Document document = Jsoup.parse(form);
		Elements paragraph = document.select("form");
		System.out.println(paragraph.html());
		
		
	}
	
	
	
	
}
