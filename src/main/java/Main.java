import executor.Python3Lexer;
import executor.Python3Parser;
import executor.Visitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import python.statement.StatementBlock;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws Exception {
    	String path = "tests/test2.py";
//        HelperParser helperParser = new HelperParser();
//        AstPrinter astPrinter = new AstPrinter();
//	    astPrinter.print(helperParser.parse(new File(path)));
	    final org.antlr.v4.runtime.CharStream stream = new ANTLRInputStream(readStream(new BufferedInputStream(new FileInputStream(path))));
	    final Python3Lexer lexer = new Python3Lexer(stream);
	    final CommonTokenStream tokens = new CommonTokenStream(lexer);
	    final Python3Parser parser = new Python3Parser(tokens);
	    final ParseTree tree = parser.prog();
	    Visitor visitor = new Visitor();
	    StatementBlock program = (StatementBlock) visitor.visit(tree);
	    program.run();
//	    final List<String> ruleNames = Arrays.asList(Python3Parser.ruleNames);
//	    final TreeViewer view = new TreeViewer(ruleNames, tree);
//	    view.open();
    }
	
	private static String readStream(InputStream is) {
		StringBuilder sb = new StringBuilder(512);
		try {
			Reader r = new InputStreamReader(is, "UTF-8");
			int c;
			while ((c = r.read()) != -1) {
				sb.append((char) c);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}