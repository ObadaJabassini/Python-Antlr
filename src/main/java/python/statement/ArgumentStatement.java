package python.statement;

import python.object.ExpressionTree;
import python.statement.Statement;

public class ArgumentStatement extends Statement
{
	public static final String notExist ="151ngfs5dfa1";
	private String name;
	private ExpressionTree tree;
	
	public ArgumentStatement(String name, ExpressionTree tree) {
		this.name = name;
		this.tree = tree;
	}
	
	@Override
	public Object run() {
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ExpressionTree getTree() {
		return tree;
	}
	
	public void setTree(ExpressionTree tree) {
		this.tree = tree;
	}
}
