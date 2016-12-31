package python.statement;

import python.object.ExpressionTree;
import python.object.PythonObject;
import python.scope.SymbolTable;

import java.util.Map;

public class ParametersStatement extends Statement
{
	protected Map<String, ExpressionTree> params;
	public ParametersStatement(Map<String, ExpressionTree> params){
		this.params = params;
	}
	@Override
	public Object run() {
		params.forEach((s, object) -> SymbolTable.getTable().addVariable(s, (PythonObject) object.run(), "paraemeter"));
		return params;
	}
	
	public Map<String, ExpressionTree> getParams() {
		return params;
	}
	
	public void setParams(Map<String, ExpressionTree> params) {
		this.params = params;
	}
}
