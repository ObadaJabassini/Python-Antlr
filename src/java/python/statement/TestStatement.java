package java.python.statement;

import java.util.ArrayList;
import java.util.List;

public class TestStatement extends Statement
{
	private List<String> operators;
	private List<ComparisonStatement> statements;
	
	public TestStatement(List<String> operators, List<ComparisonStatement> statements) {
		this.operators = operators;
		this.statements = statements;
	}
	
	@Override
	public Object run() {
		List<Boolean> bs = new ArrayList<>();
		boolean b = true;
		for (int i = 0; i < operators.size(); i++) {
			if(operators.get(i).equals("&&"))
				b = b && (Boolean) statements.get(i).run();
			else
			{
				bs.add(b);
				b = true;
			}
		}
		return bs.stream().anyMatch(aBoolean -> aBoolean);
	}
	
	public void addCondition(String op, ComparisonStatement statement){
		operators.add(op);
		statements.add(statement);
	}
	
	public List<String> getOperators() {
		return operators;
	}
	
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}
	
	public List<ComparisonStatement> getStatements() {
		return statements;
	}
	
	public void setStatements(List<ComparisonStatement> statements) {
		this.statements = statements;
	}
}
