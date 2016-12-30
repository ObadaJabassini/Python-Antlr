package python.statement;

public class BreakStatement extends Statement
{
	@Override
	public Object run() {
		return LoopBreakType.BREAK;
	}
}
