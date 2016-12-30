package python.object;

import python.scope.SymbolTable;
import python.statement.Statement;

import java.util.Map;

public class PythonParameters extends Statement
{
	protected Map<String, PythonObject> params;
	public PythonParameters(Map<String, PythonObject> params){
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
