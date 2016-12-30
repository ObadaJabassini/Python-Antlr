package python.object;

import python.statement.Statement;

import java.util.List;

public class PythonBlock extends Statement
{
	private List<Statement> statements;
	
	public PythonBlock(List<Statement> statements){
		this.statements = statements;
	}
	@Override
	public Object run() {
		statements.forEach(Statement::run);
		return statements;
	}
}
