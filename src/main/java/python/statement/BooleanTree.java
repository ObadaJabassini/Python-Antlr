package python.statement;

import python.object.PythonBoolean;

import java.util.ArrayList;
import java.util.List;

public class BooleanTree extends Statement
{
	private BooleanTree.Node root = new BooleanTree.Node();
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public Node getRoot() {
		return root;
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
		this.root.op = op;
	}
	
	public void addChild(Node node){
		this.root.children.add(node);
	}
	
	public void addChild(BooleanTree tree){
		this.root.children.add(tree.root);
	}
	
	private PythonBoolean eval(Node node){
		if(node.statement != null)
			return (PythonBoolean) node.statement.run();
		if( node.children.size() == 1)
			return eval(node.children.get(0));
		return node.children.stream().map(this::eval).reduce((f, s) -> (PythonBoolean) f.apply(s, node.op)).get();
	}
}
