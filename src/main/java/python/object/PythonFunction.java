package python.object;

import python.scope.SymbolTable;
import python.statement.ParametersStatement;
import python.statement.StatementBlock;

public class PythonFunction extends PythonObject
{
	protected String name;
	protected StatementBlock body;
	protected ParametersStatement parameters;
	
	public PythonFunction(String name, StatementBlock body, ParametersStatement parameters) {
		this.name = name;
		this.body = body;
		this.parameters = parameters;
	}
	
	private PythonObject call(){
		parameters.run();
		for (int i = 0; i < body.getStatements().size(); i++) {
			Object object = body.getStatements().get(i).run();
			if(object instanceof ReturnStatement ) {
				SymbolTable.getTable().endScope();
				return (PythonObject) ((ReturnStatement) object).run();
			}
		}
		SymbolTable.getTable().endScope();
		return Python.none();
	}
	
	@Override
	public Object run() {
		return call();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public StatementBlock getBody() {
		return body;
	}
	
	public void setBody(StatementBlock body) {
		this.body = body;
	}
	
	public ParametersStatement getParameters() {
		return parameters;
	}
	
	public void setParameters(ParametersStatement parameters) {
		this.parameters = parameters;
	}
	
	public void setParameters(ArgumentListStatement arguments){
		parameters.setParams(arguments);
	}
}
