package python.statement;

import java.util.List;

public class ArgumentListStatement extends Statement
{
	private List<ArgumentStatement> arguments;
	
	public ArgumentListStatement(List<ArgumentStatement> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public Object run() {
		return null;
	}
	
	public List<ArgumentStatement> getArguments() {
		return arguments;
	}
	
	public void setArguments(List<ArgumentStatement> arguments) {
		this.arguments = arguments;
	}
}
