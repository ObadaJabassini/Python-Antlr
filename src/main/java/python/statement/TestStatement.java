package python.statement;

public class TestStatement extends ExpressionTree
{
	private BooleanTree tree;
	@Override
	public Object run() {
		return tree.run();
	}
	
	public TestStatement(BooleanTree tree){
		super(null);
		this.tree = tree;
	}
	
	public BooleanTree getTree() {
		return tree;
	}
	
	public void setTree(BooleanTree tree) {
		this.tree = tree;
	}
}
