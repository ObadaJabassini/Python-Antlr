package python.object;

public class Python
{
	private static final PythonObject nil = new PythonNil();
	public static PythonObject True() {
		return new PythonBoolean(true);
	}
	
	public static PythonObject False() {
		return new PythonBoolean(false);
	}
	
	public static PythonObject none(){
		return nil;
	}
	
	public static class PythonNil extends PythonObject{
		private PythonNil(){
			
		}
	}
}
	
