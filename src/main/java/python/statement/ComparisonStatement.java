package python.statement;

import python.object.PythonObject;

import java.util.List;

public class ComparisonStatement extends Statement
{
	private boolean not = false;
	private List<String> ops;
	private List<ExpressionTree> trees;
	
	public ComparisonStatement(List<String> ops, List<ExpressionTree> trees) {
		this.ops = ops;
		this.trees = trees;
	}
	
	public void flipNot(){
		this.not = !this.not;
	}
	
	@Override
	public Object run() {
		if(trees.size() == 1) {
			PythonObject temp = (PythonObject) ((PythonObject)trees.get(0).run()).run();
			return temp.apply(not ? "!" : "").run();
		}
		PythonObject object = ((PythonObject)trees.get(0).run()).apply((PythonObject)trees.get(1).run(), ops.get(0));
		for (int i = 1; i < ops.size(); i++) {
			object = ((PythonObject)object.run()).apply((PythonObject) trees.get(i + 1).run(), ops.get(i));
		}
		return ((PythonObject)object.run()).apply(not?"!":"").run();
	}
	
	@Override
	public String toString() {
		return ops.toString();
	}
}
