package java.python.scope;

import java.python.error.ExceptionManager;
import java.python.error.UndefinedException;

import java.python.object.PythonObject;
import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope
{
	private Map<String, PythonObject> globalVariables = new HashMap<>();
	
	public GlobalScope() {
		super("global", null);
	}
	
	public void setGlobalVariable(String name, PythonObject object){
		globalVariables.put(name, object);
	}
	
	@Override
	public PythonObject get(String name) {
		if(!globalVariables.containsKey(name))
		{
			ExceptionManager.getManager().add(new UndefinedException(1,1, name, super.name));
		}
		return globalVariables.get(name);
	}

	@Override
	public void remove(String name) {

	}
}
