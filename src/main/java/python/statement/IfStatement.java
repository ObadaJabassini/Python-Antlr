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
		System.out.println(ifCond.run());
		if(((PythonBoolean)ifCond.run()).getValue()) {
			for (int i = 0; i < ifBody.getStatements().size(); i++) {
				Object object = ifBody.getStatements().get(i);
				if(object instanceof ReturnStatement )
					return object;
				object = ((Statement)object).run();
				if(object instanceof LoopBreakType){
					break;
				}
			}
			return this;
		}
		for (int i = 0; i < elifCond.size(); i++) {
			if(((PythonBoolean)elifCond.get(i).run()).getValue()){
				for (int j = 0; j < elifBody.get(i).getStatements().size(); j++) {
					Object object = elifBody.get(i).getStatements().get(j).run();
					if(object instanceof ReturnStatement )
						return object;
					object = ((Statement)object).run();
					if ( object == LoopBreakType.CONTINUE )
						return new ContinueStatement();
					if ( object == LoopBreakType.BREAK )
						return new BreakStatement();
				}
			}
		}
		if(elseBody != null){
			for (int i = 0; i < elseBody.getStatements().size(); i++) {
				Object object = ifBody.getStatements().get(i);
				if(object instanceof ReturnStatement )
					return object;
				object = ((Statement)object).run();
				if ( object == LoopBreakType.CONTINUE )
					return new ContinueStatement();
				if ( object == LoopBreakType.BREAK )
					return new BreakStatement();
			}
		}
		return this;
	}
	
}
