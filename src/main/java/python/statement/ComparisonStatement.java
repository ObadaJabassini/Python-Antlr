package python.statement;

import python.object.PythonComparable;
import python.object.PythonObject;

import java.util.List;

public class ComparisonStatement extends Statement
{
	private List<String> ops;
	private List<ExpressionStatement> statements;
	private boolean not = false;
	
	public ComparisonStatement(List<String> ops, List<ExpressionStatement> statements) {
		this.ops = ops;
		this.statements = statements;
	}
	
	@Override
	public Object run() {
		for (int i = 0; i < ops.size(); i++) {
			if(!((PythonComparable)statements.get(i).run()).compareTo((PythonObject) statements.get(i + 1).run(), ops.get(i))){
				return false;
			}
		}
		return true;
	}
	
	public List<String> getOps() {
		return ops;
	}
	
	public void setOps(List<String> ops) {
		this.ops = ops;
	}
	
	public List<ExpressionStatement> getStatements() {
		return statements;
	}
	
	public void setStatements(List<ExpressionStatement> statements) {
		this.statements = statements;
	}
	
	public void setNot(boolean not){
		this.not = not;
	}
	
	public boolean getNot(){
		return not;
	}
}
