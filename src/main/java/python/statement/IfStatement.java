package python.statement;

import python.object.PythonBoolean;
import python.object.PythonReturn;

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
				Object object = ifBody.getStatements().get(i).run();
				if(object instanceof PythonReturn)
					return ((PythonReturn)object).run();
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
					if(object instanceof PythonReturn)
						return ((PythonReturn)object).run();
					if(object instanceof LoopBreakType){
						break;
					}
				}
			}
		}
		if(elseBody != null){
			for (int i = 0; i < elseBody.getStatements().size(); i++) {
				Object object = elseBody.getStatements().get(i).run();
				if(object instanceof PythonReturn)
					return ((PythonReturn)object).run();
				if(object instanceof LoopBreakType){
					break;
				}
			}
		}
		return this;
	}
	
}
