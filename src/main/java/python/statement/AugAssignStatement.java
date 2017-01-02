package python.statement;

import python.object.PythonObject;
import python.scope.SymbolTable;

import java.util.List;

public class AugAssignStatement extends Statement
{
	private String op;
	private List<String> names;
	private List<ExpressionTree> trees;
	
	public AugAssignStatement(String op, List<String> names, List<ExpressionTree> trees) {
		this.op = op;
		this.names = names;
		this.trees = trees;
	}
	
	@Override
	public Object run() {
		for (int i = 0; i < names.size(); i++) {
			SymbolTable.addVariable(names.get(i), SymbolTable.lookup(names.get(i)).apply((PythonObject) trees.get(i).run(), getOp(op)), "local");
		}
		return this;
	}
	
	private String getOp(String str){
		switch (str){
			case "+=":
				return "+";
			case "-=":
				return "-";
			case "*=":
				return "*";
			case "/=":
				return "/";
			case "|=":
				return "|";
			case "&=":
				return "&";
			case "%=":
				return "%";
			case "^=":
				return "^";
			case "<<=":
				return "<<";
			case ">>=":
				return ">>";
			case "//=":
				return "//";
			case "**=":
				return "**";
		}
		return null;
	}
}
