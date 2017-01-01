package python.statement;

import python.object.PythonError;
import python.object.ReturnStatement;

public class TryStatement extends Statement
{
	private StatementBlock tryBlock, exceptBlock;
	
	public TryStatement(StatementBlock tryBlock, StatementBlock exceptBlock) {
		this.tryBlock = tryBlock;
		this.exceptBlock = exceptBlock;
	}
	
	@Override
	public Object run() {
		boolean except = false;
		for (int i = 0; i < tryBlock.getStatements().size(); i++) {
			Object object = tryBlock.getStatements().get(i);
			if(object instanceof ReturnStatement ){
				return object;
			}
			((Statement)object).run();
			if(object instanceof PythonError){
				except = true;
				break;
			}
		}
		if(except){
			for (int i = 0; i < exceptBlock.getStatements().size(); i++) {
				Object object = tryBlock.getStatements().get(i);
				if(object instanceof ReturnStatement ){
					return object;
				}
				((Statement)object).run();
				if(object instanceof PythonError){
					except = true;
					break;
				}
			}
		}
		return this;
	}
}
