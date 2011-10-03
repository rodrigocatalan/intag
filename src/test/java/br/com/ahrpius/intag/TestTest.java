package br.com.ahrpius.intag;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestTest {
	
	private final char N =  '\n';
	
	@Test
	public void testTagAnchor() {
		
		String expected = 
			"<a href=\"http://ahrpius.com.br\">" + N +
			"	ahrpius devstudio" + N +
			"</a>"; 
		
		InTag tag = new InTag("a");
		
		tag
			.addParam("href", "http://ahrpius.com.br")
			.add("ahrpius devstudio");
		
		String result = tag.toString();
		System.out.println(result);
		assertEquals(expected, result);
		
	}
	
	@Test
	public void testSingleTagLineBreak() {
		
		InTag tag = new InTag("br");
		
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
		
		InTag tag = new InTag("a", "'");
		
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
		
		InTag tag = new InTag("span");
		
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
		
		InTag form = new InTag("form");
		
		form.addParam("action", "/doSomething");
		
		InTag fieldset = form.createTag("fieldset");
		fieldset.createTag("legend").add("legend");
		
		InTag select = fieldset.createTag("select");
		select.createTag("option").add("one");
		select.createTag("option").add("two");
		select.createTag("option").add("three");
		
		fieldset.createTag("br").makeSingle();
		
		String content = form.toString();
		
		assertEquals(expected, content);
	}

}
