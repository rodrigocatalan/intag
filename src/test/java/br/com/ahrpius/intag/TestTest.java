package br.com.ahrpius.intag;

import static br.com.ahrpius.intag.nameling.Name.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.com.ahrpius.intag.InTag;


public class TestTest {
	
	private final String N =  "\n";
	
	@Test
	public void testTagAnchor() {
		
		String expected = 
			"<a href=\"http://ahrpius.com.br\">" + N +
			"	ahrpius devstudio" + N +
			"</a>"; 
		
		InTag tag = new InTag(ANCHOR);
		
		tag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		String result = tag.toString();
		System.out.println(result);
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testSingleTagLineBreak() {
		
		InTag tag = new InTag(LINE_BREAK);
		
		tag.makeSingle();
		
		String result = tag.toString();

		assertEquals("<br/>", result);
		
	}
	
	@Test
	public void testOtherQuote() {
		
		String expected = 
			"<a href='http://ahrpius.com.br'>" + N +
			"	ahrpius devstudio" + N +
			"</a>";
		
		InTag tag = new InTag(ANCHOR, "'");
		
		tag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		String result = tag.toString();
		
		assertEquals(expected, result);

	}

	@Test
	public void testMultipleParams() {
		
		String expected = 
			"<span class=\"clazz1 clazz2\">" + N +
			"	ahrpius devstudio" + N +
			"</span>";
		
		InTag tag = new InTag(SPAN);
		
		tag
			.addParam("class", "clazz1", "clazz2")
			.add("ahrpius devstudio");
		
		String result = tag.toString();
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testIndentations(){
		
		String expected = 
			"<form action=\"/doSomething\">" + N +
			"	<fieldset>" + N +
			"		<legend>" + N +
			"			legend" + N +
			"		</legend>" + N +
			"		<select>" + N +
			"			<option>" + N +
			"				one" + N +
			"			</option>" + N +
			"			<option>" + N +
			"				two" + N +
			"			</option>" + N +
			"			<option>" + N +
			"				three" + N +
			"			</option>" + N +
			"		</select>" + N +
			"		<br/>" + N +
			"	</fieldset>" + N +
			"</form>";
		
		InTag form = new InTag(FORM);
		
		form.addParam("action", "/doSomething");
		
		InTag fieldset = form.createTag(FIELDSET);
		fieldset.createTag(LEGEND).add("legend");
		
		InTag select = fieldset.createTag(SELECT);
		select.createTag(OPTION).add("one");
		select.createTag(OPTION).add("two");
		select.createTag(OPTION).add("three");
		
		fieldset.createTag(LINE_BREAK).makeSingle();
		
		String content = form.toString();
		
		assertEquals(expected, content);
	}

}
