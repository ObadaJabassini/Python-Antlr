package python.statement;

import python.object.PythonString;
import python.scope.SymbolTable;

public class DeleteStatement extends Statement
{
	private ExpressionTree tree;
	public DeleteStatement(ExpressionTree tree){
		this.tree = tree;
	}
	@Override
	public Object run() {
		SymbolTable.removeVariable(((PythonString)tree.run()).getValue());
		return this;
	}
}
