package java.python.object;

import java.python.error.ExceptionManager;
import java.python.error.UnsupportedException;
import java.util.ArrayList;

public class PythonList extends PythonObject
{
    protected ArrayList<PythonObject> value;

    public PythonList(String str) {}

    public PythonList(ArrayList<PythonObject> obj) { value = obj; }

    public ArrayList<PythonObject> getValue()
    {
        return value;
    }

    @Override
    public PythonObject apply(PythonObject second, String op)
    {
        switch (op){
            case "+":
                if(second instanceof PythonList) {
                    PythonList t = (PythonList) second;
                    ArrayList<PythonObject> arr = new ArrayList<>(getValue());
                    arr.addAll(t.getValue());
                    return new PythonList(arr);
                }
                break;
            case "*":
                if(second instanceof PythonInteger) {
                    PythonInteger number = (PythonInteger) second;
                    ArrayList<PythonObject> arr = new ArrayList<PythonObject>();
                    for (int i = 0; i < number.getValue(); i++)
                    {
                        arr.addAll(getValue());
                    }
                    return new PythonList(arr);
                }
                break;
            case "and":
                return second;
            case "or":
                return new PythonList(getValue());
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