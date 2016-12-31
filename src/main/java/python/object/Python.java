package python.object;

public class Python
{
	
	public static PythonObject True() {
		return new PythonBoolean(true);
	}
	
	public static PythonObject False() {
		return new PythonBoolean(false);
	}
}
	
