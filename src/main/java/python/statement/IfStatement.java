package python.statement;

import python.object.PythonBoolean;

import java.util.List;

public class IfStatement extends Statement
{
	private TestStatement ifCond;
	private StatementBlock ifBody, elseBody;
	private List<TestStatement> elifCond;
	private List<StatementBlock> elifBody;
	
	public IfStatement(TestStatement ifCond, StatementBlock ifBody, StatementBlock elseBody, List<TestStatement> elifCond, List<StatementBlock> elifBody) {
		this.ifCond = ifCond;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
		this.elifCond = elifCond;
		this.elifBody = elifBody;
	}
	
	@Override
	public Object run() {
		if(((PythonBoolean)ifCond.run()).getValue()) {
			for (int i = 0; i < ifBody.getStatements().size(); i++) {
				Object res = ifBody.getStatements().get(i);
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
			return this;
		}
		for (int i = 0; i < elifCond.size(); i++) {
			if(((PythonBoolean)elifCond.get(i).run()).getValue()){
				for (int j = 0; j < elifBody.get(i).getStatements().size(); j++) {
					Object res = elifBody.get(i).getStatements().get(j);
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
		}
		if(elseBody != null){
			for (int i = 0; i < elseBody.getStatements().size(); i++) {
				Object res = elseBody.getStatements().get(i);
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
