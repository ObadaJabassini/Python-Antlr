package python.statement;

import python.scope.SymbolTable;

import java.util.List;

public class GlobalStatement extends Statement
{
	private List<String> vars;
	
	public GlobalStatement(List<String> vars){
		this.vars = vars;
	}
	
	@Override
	public Object run() {
		this.vars.forEach(var -> SymbolTable.toGlobal(var));
		return this;
	}
}
