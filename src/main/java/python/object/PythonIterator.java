package python.object;

import java.util.List;

public class PythonIterator extends PythonObject
{
	int i = 0;
	private List<PythonObject> objects;
	
	public PythonIterator(List<PythonObject> objects) {
		this.objects = objects;
	}
	
	public boolean hasNext(){
		return i < objects.size();
	}
	
	public PythonObject next(){
		return objects.get(i++);
	}
}
