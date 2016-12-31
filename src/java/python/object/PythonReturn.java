package java.python.object;

import java.python.statement.Statement;

public class PythonReturn extends Statement
{
	private PythonExpression expression;
	public PythonReturn(PythonExpression expression){
		this.expression = expression;
	}
	
	@Override
	public Object run() {
		return expression.run();
	}
}