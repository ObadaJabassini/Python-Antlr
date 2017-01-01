package java.python.object;

public interface PythonOperation
{
	PythonObject apply(PythonObject second, String op);
	PythonObject apply(String op);
}
