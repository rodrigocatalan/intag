package br.com.ahrpius.test;

import static br.com.ahrpius.intag.nameling.Name.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ahrpius.intag.InTag;


public class TestTest {
	
	@Test
	public void testTagAnchor() {
		
		InTag tag = new InTag(ANCHOR);
		
		tag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		
		String content = tag.toString();
		
		assertEquals("<a href=\"http://ahrpius.com.br\">ahrpius devstudio</a>", content);
		
	}
	
	@Test
	public void testSingleTagLineBreak() {
		
		InTag tag = new InTag(LINE_BREAK);
		
		tag.
			makeSingle();
		
		String content = tag.toString();
		
		assertEquals("<br />", content);
		
	}
	
	@Test
	public void testOtherQuote() {
		
		final String RESULT = "<a href='http://ahrpius.com.br'>ahrpius devstudio</a>";
		
		InTag tag = new InTag(ANCHOR, '\'');
		
		tag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		
		String content = tag.toString();
		
		assertEquals(RESULT, content);
		
		InTag similarTag = new InTag(ANCHOR, "'");
		
		similarTag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		
		String similarContent = similarTag.toString();
		
		assertEquals(RESULT, similarContent);
	}

	@Test
	public void testMultipleParams() {
		InTag tag = new InTag(SPAN);
		
		tag
			.addParam("class", "clazz1", "clazz2")
			.add("ahrpius devstudio");
		
		
		String content = tag.toString();
		
		assertEquals("<span class=\"clazz1 clazz2\">ahrpius devstudio</span>", content);
	}

	
	public static void main(String[] args) {
		InTag input = new InTag(INPUT);
		
		input
			.addParam("type", "text")
			.addParam("name", "meuInput")
			.addParam("value", "509");
		
		String content = input.toString();
		
		System.out.println(content);
	}
}
