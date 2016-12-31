package java.python.statement;

import java.python.scope.SymbolTable;

public class PrintStatement extends Statement
{
	private String name;
	@Override
	public void run() {
		System.out.println(SymbolTable.getTable().lookup(name));
	}
	
	public void setName(String name){
		this.name = name;
	}
}
