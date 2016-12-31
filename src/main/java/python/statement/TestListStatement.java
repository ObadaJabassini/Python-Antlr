package python.statement;

import python.object.ExpressionTree;
import python.object.PythonObject;
import python.object.PythonTuple;

import java.util.ArrayList;
import java.util.List;

public class TestListStatement extends Statement
{
	private List<ExpressionTree> trees;
	
	public TestListStatement(List<ExpressionTree> trees) {
		this.trees = trees;
	}
	
	@Override
	public Object run() {
		List<PythonObject> objects = new ArrayList<>();
		//PythonTuple tuple = new PythonTuple(trees.stream().map(ExpressionTree::run).collect(Collectors.toList()));
		for (int i = 0; i < trees.size(); i++) {
			objects.add((PythonObject) trees.get(i).run());
		}
		return new PythonTuple(objects);
	}
	
	public List<ExpressionTree> getTrees() {
		return trees;
	}
	
	public void setTrees(List<ExpressionTree> trees) {
		this.trees = trees;
	}
}
