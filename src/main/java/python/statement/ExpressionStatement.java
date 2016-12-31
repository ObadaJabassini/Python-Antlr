package python.statement;

import python.object.ExpressionTree;

public class ExpressionStatement extends Statement
{
	private ExpressionTree tree;
	@Override
	public Object run() {
		return tree.evaluate();
	}
	
}
