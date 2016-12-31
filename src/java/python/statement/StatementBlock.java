package java.python.statement;

import java.util.List;

public class StatementBlock extends Statement
{
	private List<Statement> statements;
	
	public StatementBlock(List<Statement> statements){
		this.statements = statements;
	}
	@Override
	public Object run() {
		statements.forEach(Statement::run);
		return statements;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}
	
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
}
