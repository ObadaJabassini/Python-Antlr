package python.statement;

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
			if(statements.get(i) instanceof BreakStatement)
				return new BreakStatement();
			if(statements.get(i) instanceof ContinueStatement)
				return new ContinueStatement();
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
