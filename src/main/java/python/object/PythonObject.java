package python.object;

import python.statement.Statement;

import java.util.Objects;

public class PythonObject extends Statement implements PythonComparable, PythonOperation
{
	public Class<? extends PythonObject> clz = getClass();
	private static final PythonObject nill = new PythonObject();
	
	@Override
	public Object run() {
		return null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(clz);
	}
	
	@Override
	public PythonBoolean compareTo(PythonObject second, String op) {
		return (PythonBoolean) Python.False();
	}
	
	@Override
	public PythonObject apply(PythonObject second, String op) {
		return null;
	}
	
	@Override
	public PythonObject apply(String op) {
		return null;
	}
	
	public static PythonObject nil(){
		return nill;
	}
}
