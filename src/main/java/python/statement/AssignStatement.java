package python.statement;

import python.object.PythonObject;
import python.scope.SymbolTable;

public class AssignStatement extends Statement
{
	private String variableName = "";
	private PythonObject object;
	@Override
	public void run() {
		SymbolTable.getTable().addVariable(variableName, object, "local");
	}
	
	public String getVariableName() {
		return variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public PythonObject getObject() {
		return object;
	}
	
	public void setObject(PythonObject object) {
		this.object = object;
	}
}
