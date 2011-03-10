package br.com.ahrpius.intag;

import java.util.ArrayList;
import java.util.List;

public class InTag {
	
	final String WORD_WRAP = "\n";
	final String WORD_TAB = "\t";
	
	final String MASK_TAG = "<%s%s>";
	final String MASK_TAG_CLOSE = "</%s>";
	final String MASK_PARAMS = "%s=%c%s%c";
	
	private final String name;
	private final Character quote;
	private Boolean isComposite = true;
	
	private List<String[]> params = new ArrayList<String[]>();
	private List<Object> contentList = new ArrayList<Object>();
	
	public InTag(final String name){
		this(name, '"');
	}
	
	public InTag(final String name, final Character quote){
		this.name = name;
		this.quote = quote;
	}
	
	public InTag(final String name, final String quote){
		this.name = name;
		this.quote = quote.charAt(0);
	}
	
	public InTag makeComposite(){
		this.isComposite = Boolean.TRUE;
		return this;
	}
	
	public InTag makeSingle(){
		this.isComposite = Boolean.FALSE;
		return this;
	}
	
	public InTag addParam(final String... strings){
		this.params.add(strings);
		return this;
	}
	
	public String toString() {
		if ( isComposite ) {
			return tagfyComposite();			
		} else {
			return tagfySingle();
		}
	}
	
	private String tagfySingle() {
		
		StringBuffer params = new StringBuffer();
		
		for (String[] param : this.params) {
			
			params.append(" ");
			
			String values = "";
			
			if ( param.length > 2 ) {
				for (int i = 1; i < param.length; i++) {
					values += " " + param[i];
				}
			} else if (param.length == 1) {
				values = param[0];
			} else {
				values = param[1];
			}
			
			params.append( String.format(MASK_PARAMS, param[0], quote, values.trim(), quote) );
		}
		
		final String TAG = String.format(MASK_TAG, this.name, params.toString()+"/");
		
		return new String(TAG);
	}
	
	private int tab = 0;
	
	private String tagfyComposite() {
		
		StringBuffer params = new StringBuffer();
		
		for (String[] param : this.params) {
			
			params.append(" ");
			
			String values = "";
			
			if ( param.length > 2 ) {
				for (int i = 1; i < param.length; i++) {
					values += " " + param[i];
				}
			} else if (param.length == 1) {
				values = param[0];
			} else {
				values = param[1];
			}			
			
			params.append( String.format(MASK_PARAMS, param[0], quote, values.trim(), quote) );
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(MASK_TAG, this.name, params.toString() ));
		sb.append(WORD_WRAP);
		for (Object o : this.contentList) {
			tab++;
			if (o instanceof InTag){
				((InTag) o).tab = tab;
			}
			sb.append(tabulate(tab));
			sb.append(o.toString());
			sb.append(WORD_WRAP);
			tab--;
		}
		sb.append(tabulate(tab));
		sb.append(String.format(MASK_TAG_CLOSE, this.name));
		
		return sb.toString();
	}
	
	private String tabulate(int size) {
		
		if (size<=0) return "";
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < size; i++) {
			sb.append(WORD_TAB);
		}
		
		return sb.toString();
	}
	
	public InTag createCompositeParam(final String name){
		InTag to = new InTag(name);
		this.contentList.add(to);
		return to;
	}
	
	public InTag add(Object value){
		this.contentList.add(value);
		return this;
	}
	
	public InTag createTag(String name){
		InTag inTag = new InTag(name);
		this.add(inTag);
		return inTag;
	}
	
}
