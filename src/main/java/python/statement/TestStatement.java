package python.statement;

public class TestStatement extends Statement
{
	private BooleanTree tree;
	@Override
	public Object run() {
		return tree.run();
	}
	
	public TestStatement(BooleanTree tree){
		this.tree = tree;
	}
}
