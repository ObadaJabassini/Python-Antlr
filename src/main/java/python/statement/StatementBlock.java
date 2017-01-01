package python.statement;

import python.object.ReturnStatement;

import java.util.List;

public class StatementBlock extends Statement
{
	private List<Statement> statements;
	
	public StatementBlock(List<Statement> statements){
		this.statements = statements;
	}
	@Override
	public Object run() {
		for (int i = 0; i < statements.size(); i++) {
			if(statements.get(i) instanceof ReturnStatement)
				return statements.get(i);
			statements.get(i).run();
		}
		return statements;
	}
	
	public List<Statement> getStatements() {
		return statements;
	}
	
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}
}
