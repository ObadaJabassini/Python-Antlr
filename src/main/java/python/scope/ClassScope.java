package python.scope;

import python.object.PythonObject;

public class ClassScope extends Scope
{
	
	public ClassScope(String name, Scope parent) {
		super(name, parent);
	}
	
	@Override
	public PythonObject get(String name) {
		return null;
	}
	
	@Override
	public void remove(String name) {
		
	}
}
