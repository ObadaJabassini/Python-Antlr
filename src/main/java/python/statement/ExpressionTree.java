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
		public String speical;
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
			if(node.speical != null)
				return ((PythonObject)node.object.run()).apply(node.speical);
			return ((PythonObject)node.object.run());
		}
		if(node.children.size() == 1){
			PythonObject object = eval(node.children.get(0));
			if ( node.speical == null )
				return ((PythonObject)object.run());
			return ((PythonObject)object.run()).apply(node.speical);
		}
		PythonObject object = node.children.stream().map(this::eval).reduce((f, s) -> f.apply((PythonObject) s.run(), node.op)).get();
		if ( node.speical == null )
			return ((PythonObject)object.run());
		return ((PythonObject)object.run()).apply(node.speical);
	}
	
	@Override
	public String toString() {
		return rot.op != null? rot.op : rot.object.toString();
	}
}
