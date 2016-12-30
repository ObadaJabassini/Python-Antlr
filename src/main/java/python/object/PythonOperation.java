package python.object;

public interface PythonOperation
{
	PythonObject apply(PythonObject second, String op);
}
