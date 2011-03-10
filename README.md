Usage
-----

###Creating a simple tag

Writing a simple tag span `<span></span>`

	InTag tag = new InTag("span");

	String result = tag.toString();

	System.out.println(result);

output:

	<span></span>

###Adding text into a tag

	InTag tag = new InTag("span");

	tag
		.add("text");

	String result = tag.toString();

	System.out.println(result);
		
output:

	<span>
		text
	</span>
	
###Adding parameters

	InTag tag = new InTag("span");
	
	tag
		.addParam("class", "myClass")
		.add("text");
		
	String result = tag.toString();
	
	System.out.println(result);
		
output:

	<span class="myClass">
		text
	</span>

or multiple parameters

	...
		.addParam("class", "myClass", "otherClass")
	...

output:

	<span class="myClass otherClass">
		text
	</span>

###Single tag

	InTag tag = new InTag("br");
	
	tag.makeSingle();
	
	String result = tag.toString();
	
	System.out.println(result);
		
output:

	<br/>