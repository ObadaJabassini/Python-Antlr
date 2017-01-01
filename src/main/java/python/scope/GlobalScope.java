package python.scope;

import python.object.PythonObject;
import python.error.ExceptionManager;
import python.error.UndefinedException;

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
		this.globalVariables.remove(name);
	}
	
	@Override
	public String toString() {
		return globalVariables.toString();
	}
}
