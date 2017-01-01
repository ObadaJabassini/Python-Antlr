package python.statement;

import python.object.PythonIterator;
import python.scope.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class ForStatement extends Statement
{
	private List<String> names;
	private List<ExpressionTree> trees;
	private StatementBlock block;
	
	public ForStatement(List<String> names, List<ExpressionTree> trees, StatementBlock block) {
		this.names = names;
		this.trees = trees;
		this.block = block;
	}
	
	@Override
	public Object run() {
		List<PythonIterator> iterators = new ArrayList<>();
		for (ExpressionTree tree : trees) {
			iterators.add((PythonIterator) tree.run());
		}
		PythonIterator iterator = iterators.get(0);
		boolean finished = false;
		while (iterator.hasNext() && !finished) {
			for (int i = 0; i < names.size(); i++) {
				SymbolTable.getTable().addVariable(names.get(i), iterators.get(i).next(), "local");
			}
			for (int j = 0; j < block.getStatements().size(); j++) {
				Object object = block.getStatements().get(j);
				if ( object instanceof ReturnStatement ) {
					return ((ReturnStatement) object).run();
				}
				object = ((Statement)object).run();
				if ( object == LoopBreakType.BREAK ) {
					finished = true;
				}
				else if(object == LoopBreakType.CONTINUE){
					break;
				}
			}
		}
		return this;
	}
}
