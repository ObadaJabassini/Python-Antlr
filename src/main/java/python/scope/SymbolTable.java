package python.scope;

import python.object.PythonObject;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable
{
	public enum ScopeType{FUNCTION, CLASS}
	private static Scope currentScope = new GlobalScope();
	private static List<String> toGlobal = new ArrayList<>();
	private SymbolTable(){
		
	}
	
	public static void toGlobal(String name){
		toGlobal.add(name);
	}
	
	public static void beginScope(String name, ScopeType type){
		Scope temp = currentScope;
		switch (type){
			case FUNCTION:
				currentScope = new FunctionScope(name, temp);
			case CLASS:
				currentScope = new ClassScope(name, temp);
		}
	}
	
	public static void endScope(){
		currentScope = currentScope.getParent();
		toGlobal.clear();
	}
	
	public static PythonObject lookup(String name){
		return currentScope.get(name);
	}
	
	public static void removeVariable(String name) {
		currentScope.remove(name);
	}
	public static void addVariable(String name, PythonObject object, Object... params){
		if(currentScope instanceof GlobalScope || toGlobal.contains(name)){
			((GlobalScope) currentScope).setGlobalVariable(name, object);
		}
		if ( currentScope instanceof FunctionScope ){
			String type = (String) params[0];
			if(type.equals("local"))
				((FunctionScope) currentScope).setLocal(name, object);
			else
				((FunctionScope) currentScope).setParameter(name, object);
		}
		System.out.println(currentScope);
	}
}
