package python.object;
public abstract class PythonNumber extends python.object.PythonObject
{
	abstract double getValue();
	
	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
