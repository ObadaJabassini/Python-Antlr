package python.statement;

import python.object.PythonObject;
import python.scope.SymbolTable;

import java.util.Map;

public class ParametersStatement extends Statement
{
	protected Map<String, PythonObject> params;
	public ParametersStatement(Map<String, PythonObject> params){
		this.params = params;
	}
	@Override
	public Object run() {
		params.forEach((s, object) -> SymbolTable.getTable().addVariable(s, object, "paraemeter"));
		return params;
	}
	
	public Map<String, PythonObject> getParams() {
		return params;
	}
	
	public void setParams(Map<String, PythonObject> params) {
		this.params = params;
	}
}
