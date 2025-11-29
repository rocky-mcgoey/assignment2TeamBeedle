package parser;

import implementations.MyQueue;
import utilities.Iterator;

/**
 * Gathers and formats XML parsing errors that pop up from the parser
 */
public class XmlErrorCollector
{
	private MyQueue<XmlError> errors;
	
	/**
	 * Constructs a new error collector.
	 */
	public XmlErrorCollector()
	{
		this.errors = new MyQueue<>();
	}
	
	/**
	 * Adds an error to the collection
	 * 
	 * @param error the error to add
	 */
	public void addError(XmlError error)
	{
		if (error != null)
		{
			errors.enqueue(error);
		}
	}
	
	/**
	 * Adds an error with the specified details.
	 * 
	 * @param lineNumber the line number where the error occurred
	 * @param errorType the type of error
	 * @param errorMessage the error message
	 * @param originalLine the original line content
	 */
	public void addError(int lineNumber, String errorType, String errorMessage, String originalLine)
	{
		XmlError error = new XmlError(lineNumber, errorType, errorMessage, originalLine);
		errors.enqueue(error);
	}
	
	/**
	 * Checks if any errors have been collected.
	 * 
	 * @return true if there are errors, false otherwise
	 */
	public boolean hasErrors()
	{
		return !errors.isEmpty();
	}
	
	/**
	 * Gets total errors collected.
	 * 
	 * @return the error count
	 */
	public int getErrorCount()
	{
		return errors.size();
	}
	
	/**
	 * Formats errors as a string for output
	 * 
	 * @return report formatted error report
	 */
	public String formatErrors()
	{
		if (!hasErrors())
		{
			return "No errors found. XML document is well-formed.";
		}
		
		StringBuilder report = new StringBuilder();
		report.append("XML Parsing Errors Found:\n");
		report.append("=========================\n\n");
		
		int errorNumber = 1;
		Iterator<XmlError> it = errors.iterator();
		
		while (it.hasNext())
		{
			XmlError error = it.next();
			report.append("Error #").append(errorNumber++).append(":\n");
			report.append(error.toString()).append("\n\n");
		}
		
		report.append("=========================\n");
		report.append("Total Errors: ").append(getErrorCount()).append("\n");
		
		return report.toString();
	}
	
	/**
	 * Formats errors to be one line per error.
	 * 
	 */
	public String formatErrorsConcise()
	{
		if (!hasErrors())
		{
			return "No errors found.";
		}
		
		StringBuilder report = new StringBuilder();
		Iterator<XmlError> it = errors.iterator();
		
		while (it.hasNext())
		{
			XmlError error = it.next();
			report.append(String.format("Line %d: %s - %s%n",
					error.getLineNumber(),
					error.getErrorType(),
					error.getErrorMessage()));
		}
		
		return report.toString();
	}
	
	/**
	 * Prints errors to console.
	 */
	public void printErrors()
	{
		System.out.println(formatErrors());
	}
	
	/**
	 * Clears errors.
	 */
	public void clear()
	{
		errors.dequeueAll();
	}
	
	/**
	 * Gets an error iterator
	 * 
	 * @return iterator over errors
	 */
	public Iterator<XmlError> iterator()
	{
		return errors.iterator();
	}
}
