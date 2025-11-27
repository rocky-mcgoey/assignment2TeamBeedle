package parser;

import java.util.List;
import java.util.ArrayList;

public class XmlLine
{
	private final int lineNumber;
	private final String original;
	private final List<String> tags;
	

	public XmlLine(int lineNumber, String original)
	{
		this.lineNumber = lineNumber;
		this.original = original;
		this.tags = new ArrayList<>();
		
		// TODO Auto-generated constructor stub
	}
	
	public int getLineNumber()
	{
		// TODO Auto-generated method stub
		return lineNumber;
	}

	public List<String> getTags()
	{
		// TODO Auto-generated method stub
		return tags;
	}
	
	public String getOriginal()
	{
		return original;
	}

}
