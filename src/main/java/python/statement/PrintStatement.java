package python.statement;

import python.scope.SymbolTable;

public class PrintStatement extends Statement
{
	private String name;
	@Override
	public Object run() {
		System.out.println(SymbolTable.lookup(name));
		return this;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
