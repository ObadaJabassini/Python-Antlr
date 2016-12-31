package java.python.statement;

import java.util.List;

public class WhileStatement extends Statement
{
	protected TestStatement conditionStatement;
	protected StatementBlock body;
	
	public WhileStatement(TestStatement conditionStatement, StatementBlock body){
		this.conditionStatement = conditionStatement;
		this.body = body;
	}
	@Override
	public Object run() {
		boolean isFinished = false;
		List<Statement> statements = body.getStatements();
		while ((Boolean) conditionStatement.run()) {
			for (int i = 0; i < statements.size() && !isFinished; i++) {
				Object res = statements.get(i).run();
				if ( res instanceof LoopBreakType ){
					LoopBreakType type = (LoopBreakType) res;
					if(type == LoopBreakType.BREAK)
						isFinished = true;
					else
						break;
				}
			}
			if ( isFinished )
				break;
		}
		return null;
	}
}
