package python.object;

import python.statement.Statement;

import java.util.Objects;

public class PythonObject extends Statement implements PythonComparable, python.object.PythonOperation
{
	public Class<? extends PythonObject> clz = getClass();
	
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
		
		return new PythonBoolean(false);
	}
	
	@Override
	public PythonObject apply(PythonObject second, String op) {
		return null;
	}
	
	@Override
	public PythonObject apply(String op) {
		return null;
	}
}
