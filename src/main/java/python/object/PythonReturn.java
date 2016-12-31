package python.object;

import python.statement.Statement;
import python.statement.TestListStatement;

public class PythonReturn extends Statement
{
	private TestListStatement statement;
	
	public PythonReturn(TestListStatement statement){
		this.statement = statement;
	}
	@Override
	public Object run() {
		return statement.run();
	}
}
