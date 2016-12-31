package java.python.object;

import java.python.statement.ParametersStatement;
import java.python.statement.StatementBlock;

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
	
	public void call(){
		body.run();
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
}
