package python.statement;

import python.object.PythonObject;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTree extends Statement
{
	protected Node rot = new Node();
	@Override
	public Object run() {
		return eval(rot);
	}
	
	public static class Node{
		public String speical = "+";
		public String op;
		public PythonObject object;
		List<Node> children = new ArrayList<>();
	}
	
	public Node getRot() {
		return rot;
	}
	
	public ExpressionTree(String op){
		this.rot.op = op;
	}
	
	public void setRoot(Node rot){
		this.rot = rot;
	}
	
	public void setSpeical(String sp){
		this.rot.speical = sp;
	}
	
	public void addChild(ExpressionTree tree){
		this.rot.children.add(tree.rot);
	}
	
	private PythonObject eval(Node node){
		if(node.op == null){
			return node.object.apply(node.speical);
		}
		if(node.children.size() == 1){
			return eval(node.children.get(0)).apply(node.speical);
		}
		return node.children.stream().map(this::eval).reduce((f, s) -> f.apply(s, node.op)).get().apply(node.speical);
	}
	
	@Override
	public String toString() {
		return rot.op != null? rot.op : rot.object.toString();
	}
}
