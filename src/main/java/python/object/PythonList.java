package python.object;

import python.error.ExceptionManager;
import python.error.UnsupportedException;

import java.util.ArrayList;
import java.util.List;

public class PythonList extends PythonTraverse
{
    protected ArrayList<PythonObject> objects;
    
    public PythonList(ArrayList<PythonObject> obj) { objects = obj; }

    public ArrayList<PythonObject> getObjects()
    {
        return objects;
    }

    public PythonObject get(int index){
        return objects.get(index);
    }
    public void set(int index, PythonObject obj){
        objects.set(index, obj);
    }

    @Override
    public PythonObject apply(PythonObject second, String op)
    {
        switch (op){
            case "+":
                if(second instanceof PythonList) {
                    PythonList t = (PythonList) second;
                    ArrayList<PythonObject> arr = new ArrayList<>(getObjects());
                    arr.addAll(t.getObjects());
                    return new PythonList(arr);
                }
                break;
            case "*":
                if(second instanceof PythonInteger) {
                    PythonInteger number = (PythonInteger) second;
                    ArrayList<PythonObject> arr = new ArrayList<PythonObject>();
                    for (int i = 0; i < number.getValue(); i++)
                    {
                        arr.addAll(getObjects());
                    }
                    return new PythonList(arr);
                }
                break;
            case "and":
                return second;
            case "or":
                return new PythonList(getObjects());
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

    @Override
    public PythonIterator iterator() {
        return new PythonListIterator(objects);
    }
    
    public static class PythonListIterator extends PythonIterator{
    	
    	private int i = 0;
    	private List<PythonObject> objects;
	
	    public PythonListIterator(List<PythonObject> objects) {
		    this.objects = objects;
	    }
	
	    public boolean hasNext(){
		    return i < objects.size();
	    }
	
	    public PythonObject next(){
		    return objects.get(i++);
	    }
    }
}
