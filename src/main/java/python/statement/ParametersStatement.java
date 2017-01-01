package python.statement;

import python.object.PythonObject;
import python.scope.SymbolTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParametersStatement extends Statement
{
	private Map<String, ExpressionTree> params;
	private List<String> original = new ArrayList<>();
	public ParametersStatement(Map<String, ExpressionTree> params){
		this.params = params;
		params.keySet().forEach(s -> original.add(s));
	}
	@Override
	public Object run() {
		params.forEach((s, object) -> SymbolTable.getTable().addVariable(s, (PythonObject) object.run(), "parameter"));
		return params;
	}
	
	public Map<String, ExpressionTree> getParams() {
		return params;
	}
	
	public void addParam(String name, ExpressionTree tree, List<String> remaining){
		if(!name.equals(ArgumentStatement.notExist)){
			params.put(name, tree);
			remaining.remove(remaining.indexOf(name));
		}
		else{
			params.put(remaining.get(0), tree);
			remaining.remove(0);
		}
	}
	
	public void setParams(Map<String, ExpressionTree> params) {
		this.params = params;
	}
	
	public void setParams(ArgumentListStatement statement){
		List<String> remaining = new LinkedList<>();
		for (String anOriginal : original) {
			remaining.add(anOriginal);
		}
//		if(statement.getArguments().size() != original.size()){
//			ExceptionManager.getManager().add(new IllegalArguments(0, 0, "number of arguments dont match"));
//			return;
//		}
		statement.getArguments().forEach(argumentStatement -> addParam(argumentStatement.getName(), argumentStatement.getTree(), remaining));
	}
}
