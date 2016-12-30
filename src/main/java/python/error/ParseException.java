package python.error;

public class ParseException
{
	protected int lineNumber, columnNumber;
	protected String message;
	
	public ParseException(int lineNumber, int columnNumber, String message) {
		this.lineNumber = lineNumber;
		this.columnNumber = columnNumber;
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public int getColumnNumber() {
		return columnNumber;
	}
}
