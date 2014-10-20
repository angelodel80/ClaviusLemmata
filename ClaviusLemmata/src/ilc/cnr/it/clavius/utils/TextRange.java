package ilc.cnr.it.clavius.utils;

public abstract class TextRange {

	protected String text;
	protected int strart;
	protected int end;
	
	public abstract <T> TextRange setStart(T start);
	public abstract <T> TextRange setEnd(T end);
	public abstract <T> TextRange setText(T content);
	
	public final int getStart(){
		return this.strart;
	}
	
	public final int getEnd(){
		return this.end;
	}
	
	public final String getText(){
		return this.text;
	}

}
