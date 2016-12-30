package python.error;

public class UndefinedException extends ParseException
{
	public UndefinedException(int lineNumber, int columnNumber, String variableName, String scopeName) {
		super(lineNumber, columnNumber, variableName + " was not defined in " + scopeName);
	}
	
}
