package python.scope;

import python.object.PythonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionScope extends Scope
{
	private Map<String, PythonObject> parameters = new HashMap<>();
	private List<String> parametersOrder = new ArrayList<>();
	private Map<String, PythonObject> locals = new HashMap<>();
	
	public FunctionScope(String name, Scope parent) {
		super(name, parent);
	}
	
	public void setParameter(int index, PythonObject object){
		parameters.put(parametersOrder.get(index), object);
	}
	public void setParameter(String name, PythonObject object){
		parameters.put(name, object);
	}
	public void setLocal(String name, PythonObject object){
		locals.put(name, object);
	}
	
	public Map<String, PythonObject> getParameters() {
		return parameters;
	}
	
	public void setParameters(Map<String, PythonObject> parameters) {
		this.parameters = parameters;
	}
	
	public List<String> getParametersOrder() {
		return parametersOrder;
	}
	
	public void setParametersOrder(List<String> parametersOrder) {
		this.parametersOrder = parametersOrder;
	}
	
	public Map<String, PythonObject> getLocals() {
		return locals;
	}
	
	public void setLocals(Map<String, PythonObject> locals) {
		this.locals = locals;
	}
	
	@Override
	public PythonObject get(String name){
		if(locals.containsKey(name))
			return locals.get(name);
		if ( parameters.containsKey(name) )
			return parameters.get(name);
		if(parent == null)
			return null;
		return parent.get(name);
	}
}
