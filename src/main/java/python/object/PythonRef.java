package python.object;

import python.scope.SymbolTable;

public class PythonRef extends PythonObject
{
	private String name;
	
	public PythonRef(String name) {
		this.name = name;
	}
	
	@Override
	public Object run() {
		return SymbolTable.lookup(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public PythonObject apply(PythonObject second, String op) {
		return SymbolTable.lookup(name).apply(second, op);
	}
	
	@Override
	public PythonObject apply(String op) {
		return SymbolTable.lookup(name).apply(op);
	}
}
