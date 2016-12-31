package java.python.statement;

public class ContinueStatement extends Statement
{
	@Override
	public Object run() {
		return LoopBreakType.CONTINUE;
	}
}
