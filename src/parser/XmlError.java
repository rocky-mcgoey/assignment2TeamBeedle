package parser;


public class XmlError
{
	private final int lineNumber;
	private final String errorType;
	private final String errorMessage;
	private final String originalLine;
	
	/**
	 * Constructs an XML error 
	 * 
	 * @param lineNumber the line number where the error occurred
	 * @param errorType the type of error
	 * @param errorMessage error message
	 * @param originalLine line content where the error occurred
	 */
	public XmlError(int lineNumber, String errorType, String errorMessage, String originalLine)
	{
		this.lineNumber = lineNumber;
		this.errorType = errorType;
		this.errorMessage = errorMessage;
		this.originalLine = originalLine;
	}
	
	/**
	 * Gets the line number where the error occurred.
	 * 
	 * @return the line number
	 */
	public int getLineNumber()
	{
		return lineNumber;
	}
	
	/**
	 * Gets the type of error.
	 * 
	 * @return the error type
	 */
	public String getErrorType()
	{
		return errorType;
	}
	
	/**
	 * Gets the detailed error message.
	 * 
	 * @return the error message
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	/**
	 * Gets the original line content where the error occurred.
	 * 
	 * @return the original line
	 */
	public String getOriginalLine()
	{
		return originalLine;
	}
	
	@Override
	public String toString()
	{
		return String.format("Line %d - %s: %s%n  Original Line: %s",
				lineNumber, errorType, errorMessage, originalLine.trim());
	}
}
