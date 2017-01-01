package python.statement;

public class ContianerStatement extends Statement
{
	private Object value;
	
	@Override
	public Object run() {
		return null;
	}
	
	public ContianerStatement(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
}
