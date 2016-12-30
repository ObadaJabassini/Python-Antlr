package python.scope;

import python.object.PythonObject;

public class SymbolTable
{
	public enum ScopeType{FUNCTION, CLASS}
	Scope currentScope = new GlobalScope();
	private static final SymbolTable table = new SymbolTable();
	private SymbolTable(){
		
	}
	
	public static SymbolTable getTable(){
		return table;
	}
	
	public void beginScope(String name, ScopeType type){
		Scope temp = currentScope;
		switch (type){
			case FUNCTION:
				currentScope = new FunctionScope(name, temp);
			case CLASS:
				currentScope = new ClassScope(name, temp);
		}
	}
	
	public void endScope(){
		currentScope = currentScope.getParent();
	}
	
	public PythonObject lookup(String name){
		return currentScope.get(name);
	}
	
	public void addVariable(String name, PythonObject object, Object... params){
		if(currentScope instanceof GlobalScope){
			((GlobalScope) currentScope).setGlobalVariable(name, object);
		}
		if ( currentScope instanceof FunctionScope ){
			String type = (String) params[0];
			if(type.equals("local"))
				((FunctionScope) currentScope).setLocal(name, object);
			else
				((FunctionScope) currentScope).setParameter(name, object);
		}
		else{
			
		}
	}
}
