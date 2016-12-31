package python.object;

import python.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTree extends Statement
{
	private Node root = new Node();
	@Override
	public Object run() {
		return eval(root);
	}
	
	public static class Node{
		public String speical = "+";
		public String op;
		public PythonObject object;
		List<Node> children = new ArrayList<>();
	}
	
	public ExpressionTree(String op){
		this.root.op = op;
	}
	
	public void setRoot(Node root){
		this.root = root;
	}
	
	public void setSpeical(String sp){
		this.root.speical = sp;
	}
	
	public void addChild(ExpressionTree tree){
		this.root.children.add(tree.root);
	}
	
	private PythonObject eval(Node node){
		if(node.op == null && node.children.isEmpty()){
			return PythonObject.nil();
		}
		if(node.op == null){
			return node.object.apply(node.speical);
		}
		if(node.children.size() == 1){
			return eval(node.children.get(0)).apply(node.speical);
		}
		return node.children.stream().map(this::eval).reduce((f, s) -> f.apply(s, node.op)).get().apply(node.speical);
	}
}
