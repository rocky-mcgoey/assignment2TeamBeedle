package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class XmlTagReader
{
	public List<XmlLine> readXmlFile(String filename) throws IOException
	{
		List<XmlLine> lines = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
		{
			String line;
			int lineNumber = 1;
			
			
			while((line = reader.readLine()) != null)
			{
				XmlLine info = new XmlLine(lineNumber, line);
				extractTags(line, info.getTags());
				lines.add(info);
				lineNumber++;
			}
		}
		return lines;
	}
	
	private void extractTags(String line, List<String> tags)
	{
		int pos = 0;
		int length = line.length();
		
		
		while (pos < length)
		{
			int start = line.indexOf('<', pos);
			if(start == -1)
			{
				return;
			}
			int end = line.indexOf('>', start +1);
			if (end == -1)
			{
				return;
			}
			
			String tag = line.substring(start, end +1).trim();
			
			if(!(tag.startsWith("<?") && tag.endsWith("?>")))
			{
				tags.add(tag);
			}
			pos = end +1;
		}
	}
}
