package python.statement;

import python.object.PythonIterator;
import python.object.PythonReturn;
import python.object.PythonTraverse;
import python.scope.SymbolTable;

import java.util.List;

public class ForStatement extends Statement
{
	private List<String> names;
	private PythonTraverse traverse;
	private StatementBlock block;
	
	public ForStatement(List<String> names, PythonTraverse traverse, StatementBlock block) {
		this.names = names;
		this.traverse = traverse;
		this.block = block;
	}
	
	@Override
	public Object run() {
		PythonIterator iterator = traverse.iterator();
		int i = 0;
		boolean finished = false;
		while (iterator.hasNext() && !finished) {
			SymbolTable.getTable().addVariable(names.get(i++), iterator.next(), "local");
			for (int j = 0; j < block.getStatements().size(); j++) {
				Object object = block.getStatements().get(j);
				if ( object instanceof PythonReturn ) {
					return ((PythonReturn) object).run();
				}
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
