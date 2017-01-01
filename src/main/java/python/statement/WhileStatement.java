package python.statement;

import python.object.PythonBoolean;
import python.object.ReturnStatement;

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
		boolean isFinished = false;
		List<Statement> statements = body.getStatements();
		while (((PythonBoolean) conditionStatement.run()).getValue()) {
			for (int i = 0; i < statements.size() && !isFinished; i++) {
				Object res = statements.get(i);
				if ( res instanceof ReturnStatement ) {
					return res;
				}
				res = ((Statement)res).run();
				if ( res == LoopBreakType.BREAK ) {
					isFinished = true;
				}
				else if ( res == LoopBreakType.CONTINUE ) {
					break;
				}
			}
			if ( isFinished ) {
				break;
			}
		}
		return this;
	}
}
