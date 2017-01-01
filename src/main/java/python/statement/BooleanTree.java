package python.statement;

import python.object.PythonObject;

import java.util.ArrayList;
import java.util.List;

public class BooleanTree extends ExpressionTree
{
	protected BooleanTree.Node root = new BooleanTree.Node();
	
	public void setRoot(Node root) {
		super.rot.op = root.op;
		this.root = root;
	}
	
	public static class Node{
		public String op;
		public ComparisonStatement statement;
		public List<Node> children = new ArrayList<>();
	}
	@Override
	public Object run() {
		return eval(root);
	}
	
	public BooleanTree(String op){
		super(null);
		this.root.op = op;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void addChild(Node node){
		this.root.children.add(node);
	}
	
	public void addChild(BooleanTree tree){
		this.root.children.add(tree.root);
	}
	
	private PythonObject eval(Node node){
		if(node.statement != null)
			return (PythonObject) node.statement.run();
		if( node.children.size() == 1)
			return eval(node.children.get(0));
		return node.children.stream().map(this::eval).reduce((f, s) -> f.apply(s, node.op)).get();
	}
	
	@Override
	public String toString() {
		return root.op != null? root.op : root.statement.toString();
	}
}
