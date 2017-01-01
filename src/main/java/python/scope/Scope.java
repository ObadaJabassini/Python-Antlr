package python.scope;

import python.object.PythonObject;

public abstract class Scope
{
	protected String name;
	protected Scope parent = null;
	
	public Scope(String name, Scope parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Scope getParent() {
		return parent;
	}
	
	public void setParent(Scope parent) {
		this.parent = parent;
	}
	
	public abstract PythonObject get(String name);
	
	public abstract void remove(String name);
	
	@Override
	public String toString() {
		return name;
	}
}
