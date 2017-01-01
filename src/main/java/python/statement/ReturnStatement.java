package python.statement;

import python.statement.Statement;
import python.statement.TestListStatement;

public class ReturnStatement extends Statement
{
	private TestListStatement statement;
	
	public ReturnStatement(TestListStatement statement){
		this.statement = statement;
	}
	@Override
	public Object run() {
		return statement.run();
	}
}
