import executor.Python3Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class AstPrinter {

    private boolean ignoringWrappers = true;

    public void setIgnoringWrappers(boolean ignoringWrappers) {
        this.ignoringWrappers = ignoringWrappers;
    }

    public void print(Python3Parser.File_inputContext ctx) {
        visit(ctx, 0);
    }

    private void visit(RuleContext ctx, int indentation) {
    	//ctx.accept(new PythonVisitor());
	    String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
	    for (int i=0;i<indentation;i++) {
		    System.out.print("  ");
	    }
	    System.out.println(ruleName);
	    for (int i=0;i<ctx.getChildCount();i++) {
		    ParseTree element = ctx.getChild(i);
		    if (element instanceof RuleContext) {
			    visit((RuleContext)element, indentation + 1);
		    }
	    }
    }
    
    private class ReturnRecord{
    	private String type;
    	private Object value;
	
	    public ReturnRecord(String type, Object value) {
		    this.type = type;
		    this.value = value;
	    }
	
	    public String getType() {
		    return type;
	    }
	
	    public void setType(String type) {
		    this.type = type;
	    }
	
	    public Object getValue() {
		    return value;
	    }
	
	    public void setValue(Object value) {
		    this.value = value;
	    }
    }
}
