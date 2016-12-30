package python.scope;

import python.statement.Statement;

public class ConditionStatement extends Statement
{
	@Override
	public Object run() {
		return true;
	}
}
