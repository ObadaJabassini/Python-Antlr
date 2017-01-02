package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

import java.util.ArrayList;
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
		return new PythonList.PythonListIterator(objects);
	}
	
	    public List<PythonObject> getValue() { return objects; }

    @Override
    public PythonObject apply(PythonObject second, String op)
    {
        switch (op){
            case "+":
                if(second instanceof PythonTuple) {
                    PythonTuple t = (PythonTuple) second;
                    ArrayList<PythonObject> arr = new ArrayList<>(getValue());
                    arr.addAll(t.getValue());
                    return new PythonTuple(arr);
                }
                break;
            case "*":
                if(second instanceof PythonInteger) {
                    PythonInteger number = (PythonInteger) second;
                    ArrayList<PythonObject> arr = new ArrayList<>();
                    for (int i = 0; i < number.getValue(); i++)
                        arr.addAll(getValue());
                    return new PythonTuple(arr);
                }
                break;
            case "and":
                return second;
            case "or":
                return new PythonTuple(getValue());
        }
        ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
        return null;
    }

    @Override
    public PythonObject apply(String op) {
        switch (op) {
            case "not":
                return new PythonBoolean(false);
        }
        ExceptionManager.getManager().add(new UnsupportedException(0, 0, "Unsupported operation"));
        return null;
    }
}
