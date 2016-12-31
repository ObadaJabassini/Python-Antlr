package python.object;

import java.util.List;

public class PythonTuple extends PythonTraverse{
	private List<PythonObject> objects;
	
	public PythonTuple(List<PythonObject> objects){
		this.objects = objects;
	}
	
	public PythonObject get(int index){
		return objects.get(index);
	}
	
	@Override
	public PythonIterator iterator() {
		return new PythonIterator(objects);
	}
}