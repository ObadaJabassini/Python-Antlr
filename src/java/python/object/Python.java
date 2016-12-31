package java.python.object;

public class Python
{
	private static final PythonObject nil = new PythonNil();
	private static final PythonObject TRUE = new PythonTrue();
	private static final PythonObject FALSE = new PythonFalse();
	public static PythonObject nil(){
		return nil;
	}
	
	public static PythonObject True(){
		return TRUE;
	}
	
	public static PythonObject False(){
		return FALSE;
	}
	
	public static class PythonNil extends PythonObject{private PythonNil(){}}
	public static class PythonTrue extends PythonObject{private PythonTrue(){}}
	public static class PythonFalse extends PythonObject{private PythonFalse(){}}}
