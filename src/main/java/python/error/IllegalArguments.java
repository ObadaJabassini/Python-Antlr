package python.error;

public class IllegalArguments extends ParseException
{
	public IllegalArguments(int lineNumber, int columnNumber, String message) {
		super(lineNumber, columnNumber, message);
	}
}
