package python.statement;

import python.object.PythonObject;
import python.scope.SymbolTable;

import java.util.List;

public class AssignStatement extends Statement
{
	private List<String> names;
	private List<ExpressionTree> trees;
	
	public AssignStatement(List<String> names, List<ExpressionTree> trees) {
		this.names = names;
		this.trees = trees;
	}
	
	@Override
	public Object run() {
		for (int i = 0; i < names.size(); i++) {
			PythonObject res = (PythonObject) trees.get(i).run();
			SymbolTable.addVariable(names.get(i), res, "local");
		}
		return this;
	}
}
