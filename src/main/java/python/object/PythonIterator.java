package python.object;

public abstract class PythonIterator extends PythonObject
{
	public abstract boolean hasNext();
	
	public abstract PythonObject next();
}
