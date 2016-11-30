package parser;

import java.io.File;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        AstPrinter astPrinter = new AstPrinter();
	    parser.parse(new File("tests/test.py"));
    }
}
