package python.statement;

import python.object.PythonBoolean;

import java.util.List;

public class WhileStatement extends Statement
{
	protected TestStatement conditionStatement;
	protected StatementBlock body;
	
	public WhileStatement(TestStatement conditionStatement, StatementBlock body) {
		this.conditionStatement = conditionStatement;
		this.body = body;
	}
	
	@Override
	public Object run() {
		List<Statement> statements = body.getStatements();
		while (((PythonBoolean) conditionStatement.run()).getValue()) {
			for (Statement statement : statements) {
				Object res = statement;
				if ( res instanceof ReturnStatement ) {
					return res;
				}
				if ( res instanceof BreakStatement ) {
					return new BreakStatement();
				}
				if ( res instanceof ContinueStatement ) {
					break;
				}
				res = ((Statement) res).run();
				if ( res instanceof ReturnStatement ) {
					return res;
				}
				if ( res instanceof BreakStatement || res == LoopBreakType.BREAK) {
					return new BreakStatement();
				}
				if ( res instanceof ContinueStatement || res == LoopBreakType.CONTINUE) {
					break;
				}
			}
		}
		return this;
	}
}
