package python.object;

public interface PythonComparable
{
	PythonBoolean compareTo(PythonObject second, String op);
}
