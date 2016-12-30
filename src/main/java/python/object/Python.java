package python.object;

public class Python
{
	private static final PythonObject nil = new PythonNil();
	public static PythonObject nil(){
		return nil;
	}
}
