package java.python.error;

public class UnsupportedException extends ParseException
{
	public UnsupportedException(int lineNumber, int columnNumber, String message) {
		super(lineNumber, columnNumber, message);
	}
}
