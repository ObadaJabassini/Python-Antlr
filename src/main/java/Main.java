import executor.Python3Lexer;
import executor.Python3Parser;
import executor.PythonVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

//GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;


public class Main
{
	//GUI
	private JButton Compile;
	private JPanel panel;
	private JTextArea textArea1;
	private JLabel CompiledOrNot;
	private JLabel Output;
	private JButton Load;
	private JLabel Path;

    public static void main(String[] args) throws Exception {
		/*GUI*/
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}

		JFrame jFrame=new JFrame("start");
		jFrame.setContentPane(new start().panel);
		jFrame.pack();
		jFrame.setVisible(true);
		/*GUI*/

    	String path = "tests/test3.py";
//        HelperParser helperParser = new HelperParser();
//        AstPrinter astPrinter = new AstPrinter();
//	    astPrinter.print(helperParser.parse(new File(path)));
	    final org.antlr.v4.runtime.CharStream stream = new ANTLRInputStream(readStream(new BufferedInputStream(new FileInputStream(path))));
	    final Python3Lexer lexer = new Python3Lexer(stream);
	    final CommonTokenStream tokens = new CommonTokenStream(lexer);
	    final Python3Parser parser = new Python3Parser(tokens);
	    final ParseTree tree = parser.prog();
	    PythonVisitor pythonVisitor = new PythonVisitor();
	    System.out.println(tree.toStringTree(parser));
	    pythonVisitor.visit(tree).run();
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

	//GUI
	public Main() {
		liseners();
	}
	private void liseners(){
		Compile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		Load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(panel);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					Path.setText("Selected file: " + selectedFile.getAbsolutePath());
				}
			}
		});
	}
}