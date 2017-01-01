package executor;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import python.object.*;
import python.scope.SymbolTable;
import python.statement.*;

import java.util.*;
import java.util.stream.Collectors;

public class PythonVisitor implements Python3Visitor<Statement>
{
	public Map<String, PythonFunction> functions = new HashMap<>();
	{
		List<Statement> statements = new ArrayList<>();
		PrintStatement printStatement = new PrintStatement();
		printStatement.setName("x");
		statements.add(printStatement);
		Map<String, ExpressionTree> trees = new LinkedHashMap<>();
		ExpressionTree tree = new ExpressionTree(null);
		ExpressionTree.Node n = new ExpressionTree.Node();
		n.object = new PythonInteger(10);
		tree.setRoot(n);
		trees.put("x", tree);
		ParametersStatement statement = new ParametersStatement(trees);
		PythonFunction function = new PythonFunction("print", new StatementBlock(statements), statement);
		functions.put("print", function);
	}
	
	@Override
	public Statement visitTestlist(@NotNull Python3Parser.TestlistContext ctx) {
		List<ExpressionTree> trees = new ArrayList<>();
		for (int i = 0; i < ctx.test().size(); i++) {
			trees.add((ExpressionTree) visitTest(ctx.test(i)));
		}
		return new TestListStatement(trees);
	}
	
	@Override
	public Statement visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitArgument(@NotNull Python3Parser.ArgumentContext ctx) {
		if(ctx.test().size() == 1){
			return new ArgumentStatement(ArgumentStatement.notExist, (ExpressionTree) visitTest(ctx.test(0)));
		}
		return new ArgumentStatement(ctx.test(0).getText(), (ExpressionTree) visitTest(ctx.test(1)));
	}
	
	@Override
	public Statement visitNot_test(@NotNull Python3Parser.Not_testContext ctx) {
		if ( ctx.NOT() != null ) {
			BooleanTree tree = new BooleanTree(null);
			BooleanTree.Node node = new BooleanTree.Node();
			node.statement = (ComparisonStatement) visitComparison(ctx.comparison());
			tree.setRoot(node);
			return tree;
		}
		BooleanTree tree = (BooleanTree) visitNot_test(ctx.not_test());
		tree.getRoot().statement.flipNot();
		return tree;
	}
	
	@Override
	public Statement visitFile_input(@NotNull Python3Parser.File_inputContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx) {
		ExpressionTree tree = new ExpressionTree("^");
		for (int i = 0; i < ctx.and_expr().size(); i++) {
			tree.addChild((ExpressionTree) visitAnd_expr(ctx.and_expr(i)));
		}
		return tree;
	}
	
	@Override
	public Statement visitInteger(@NotNull Python3Parser.IntegerContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitImport_from(@NotNull Python3Parser.Import_fromContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitSingle_input(@NotNull Python3Parser.Single_inputContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitDecorated(@NotNull Python3Parser.DecoratedContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitWith_item(@NotNull Python3Parser.With_itemContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitRaise_stmt(@NotNull Python3Parser.Raise_stmtContext ctx) {
		return new ReturnStatement(new TestListStatement(new ArrayList<>()));
	}
	
	@Override
	public Statement visitImport_as_name(@NotNull Python3Parser.Import_as_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitExcept_clause(@NotNull Python3Parser.Except_clauseContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitCompound_stmt(@NotNull Python3Parser.Compound_stmtContext ctx) {
		if ( ctx.if_stmt() != null ) {
			return visitIf_stmt(ctx.if_stmt());
		}
		if ( ctx.while_stmt() != null ) {
			return visitWhile_stmt(ctx.while_stmt());
		}
		if ( ctx.for_stmt() != null ) {
			return visitFor_stmt(ctx.for_stmt());
		}
		if ( ctx.try_stmt() != null ) {
			return visitTry_stmt(ctx.try_stmt());
		}
		if ( ctx.classdef() != null ) {
			return visitClassdef(ctx.classdef());
		}
		if ( ctx.funcdef() != null ) {
			return visitFuncdef(ctx.funcdef());
		}
		if ( ctx.with_stmt() != null ) {
			return visitWith_stmt(ctx.with_stmt());
		}
		if ( ctx.decorated() != null ) {
			return visitDecorated(ctx.decorated());
		}
		return null;
	}
	
	@Override
	public Statement visitNumber(@NotNull Python3Parser.NumberContext ctx) {
		ExpressionTree tree = new ExpressionTree(null);
		ExpressionTree.Node node = new ExpressionTree.Node();
		if ( ctx.integer() != null ) {
			node.object = new PythonInteger(ctx.integer().getText());
		}
		if ( ctx.FLOAT_NUMBER() != null ) {
			node.object = new PythonFloat(ctx.FLOAT_NUMBER().getText());
		}
		else {
			node.object = new PythonComplex(ctx.IMAG_NUMBER().getText());
		}
		tree.setRoot(node);
		return tree;
	}
	
	@Override
	public Statement visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx) {
		ExpressionTree tree = new ExpressionTree("&");
		for (int i = 0; i < ctx.shift_expr().size(); i++) {
			tree.addChild((ExpressionTree) visitShift_expr(ctx.shift_expr(i)));
		}
		return tree;
	}
	
	@Override
	public Statement visitLambdef_nocond(@NotNull Python3Parser.Lambdef_nocondContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitDictorsetmaker(@NotNull Python3Parser.DictorsetmakerContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitReturn_stmt(@NotNull Python3Parser.Return_stmtContext ctx) {
		return new ReturnStatement((TestListStatement)visitTestlist(ctx.testlist()));
	}
	
	@Override
	public Statement visitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx) {
		if(ctx.break_stmt() != null){
			return visitBreak_stmt(ctx.break_stmt());
		}
		if(ctx.continue_stmt() != null){
			return visitContinue_stmt(ctx.continue_stmt());
		}
		if(ctx.return_stmt() != null){
			return visitReturn_stmt(ctx.return_stmt());
		}
		if(ctx.raise_stmt() != null){
			return visitRaise_stmt(ctx.raise_stmt());
		}
		if(ctx.yield_stmt() != null){
			return visitYield_stmt(ctx.yield_stmt());
		}
		return null;
	}
	
	@Override
	public Statement visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx) {
		TestStatement conditionStatement = (TestStatement) visitTest(ctx.test());
		StatementBlock statementBlock = (StatementBlock) visitSuite(ctx.suite(0));
		return new WhileStatement(conditionStatement, statementBlock);
	}
	
	@Override
	public Statement visitOr_test(@NotNull Python3Parser.Or_testContext ctx) {
		if ( ctx.OR().size() == 0 ) {
			return visitAnd_test(ctx.and_test(0));
		}
		BooleanTree tree = new BooleanTree("||");
		for (int i = 0; i < ctx.OR().size(); i++) {
			tree.addChild((BooleanTree) visitAnd_test(ctx.and_test(i)));
		}
		return tree;
	}
	
	@Override
	public Statement visitComparison(@NotNull Python3Parser.ComparisonContext ctx) {
		if(ctx.star_expr().size() == 1){
			return new ComparisonStatement(null, new ArrayList<ExpressionTree>() {{visitStar_expr(ctx.star_expr(0));}});
		}
		List<String> ops = new ArrayList<>();
		List<ExpressionTree> trees = new ArrayList<>();
		trees.add((ExpressionTree) visitStar_expr(ctx.star_expr(0)));
		for (int i = 0; i < ctx.comp_op().size(); i++) {
			ops.add(ctx.comp_op(i).getText());
			trees.add((ExpressionTree) visitStar_expr(ctx.star_expr(i + 1)));
		}
		return new ComparisonStatement(ops, trees);
	}
	
	@Override
	public Statement visitTest(@NotNull Python3Parser.TestContext ctx) {
		return visitOr_test(ctx.or_test(0));
	}
	
	@Override
	public Statement visitSubscript(@NotNull Python3Parser.SubscriptContext ctx) {
		return visitTest(ctx.test(0));
	}
	
	@Override
	public Statement visitComp_for(@NotNull Python3Parser.Comp_forContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitYield_arg(@NotNull Python3Parser.Yield_argContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitYield_expr(@NotNull Python3Parser.Yield_exprContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitImport_stmt(@NotNull Python3Parser.Import_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitShift_expr(@NotNull Python3Parser.Shift_exprContext ctx) {
		if ( ctx.arith_expr().size() == 1 ) {
			return visitArith_expr(ctx.arith_expr(0));
		}
		ExpressionTree tree = new ExpressionTree(ctx.opss.get(0).getText());
		tree.addChild((ExpressionTree) visitArith_expr(ctx.arith_expr(0)));
		for (int i = 1; i < ctx.opss.size(); i++) {
			ExpressionTree tree1 = new ExpressionTree(ctx.opss.get(i).getText());
			tree1.addChild((ExpressionTree) visitArith_expr(ctx.arith_expr(i + 1)));
			tree.addChild(tree1);
		}
		return tree;
	}
	
	@Override
	public Statement visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAnd_test(@NotNull Python3Parser.And_testContext ctx) {
		if ( ctx.AND().size() == 0 ) {
			return visitNot_test(ctx.not_test(0));
		}
		BooleanTree tree = new BooleanTree("&&");
		for (int i = 0; i < ctx.AND().size(); i++) {
			tree.addChild((BooleanTree) visitNot_test(ctx.not_test(i)));
		}
		return tree;
	}
	
	@Override
	public Statement visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
		List<String> vars = new ArrayList<>();
		for (int i = 0; i < ctx.NAME().size(); i++) {
			vars.add(ctx.NAME(i).getText());
		}
		return new GlobalStatement(vars);
	}
	
	@Override
	public Statement visitImport_as_names(@NotNull Python3Parser.Import_as_namesContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitDecorators(@NotNull Python3Parser.DecoratorsContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTry_stmt(@NotNull Python3Parser.Try_stmtContext ctx) {
		return new TryStatement((StatementBlock) visitSuite(ctx.suite(0)), (StatementBlock) visitSuite(ctx.suite(1)));
	}
	
	@Override
	public Statement visitComp_op(@NotNull Python3Parser.Comp_opContext ctx) {
		return Python.none();
	}
	
	@Override
	public Statement visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx) {
		return visitExpr(ctx.expr());
	}
	
	@Override
	public Statement visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
		return new BreakStatement();
	}
	
	@Override
	public Statement visitParameters(@NotNull Python3Parser.ParametersContext ctx) {
		if ( ctx.typedargslist() != null ) {
			return new ParametersStatement(new LinkedHashMap<>());
		}
		return visitTypedargslist(ctx.typedargslist());
	}
	
	@Override
	public Statement visitDecorator(@NotNull Python3Parser.DecoratorContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTfpdef(@NotNull Python3Parser.TfpdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitString(@NotNull Python3Parser.StringContext ctx) {
		ExpressionTree tree = new ExpressionTree(null);
		ExpressionTree.Node node = new ExpressionTree.Node();
		node.object = new PythonString(ctx.STRING_LITERAL().getText());
		tree.setRoot(node);
		return tree;
	}
	
	@Override
	public Statement visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx) {
		List<TestStatement> testStatements = new ArrayList<>();
		List<StatementBlock> blocks = new ArrayList<>();
		for (int i = 0; i < ctx.elifTest.size(); i++) {
			testStatements.add((TestStatement) visitTest(ctx.elifTest.get(i)));
			blocks.add((StatementBlock) visitSuite(ctx.suite(i + 1)));
		}
		StatementBlock b = null;
		if ( ctx.ELSE() != null ) {
			b = (StatementBlock) visitSuite(ctx.elseSuite);
		}
		return new IfStatement((TestStatement) visitTest(ctx.test(0)), (StatementBlock) visitSuite(ctx.suite(0)), b, testStatements, blocks);
	}
	
	@Override
	public Statement visitWith_stmt(@NotNull Python3Parser.With_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitClassdef(@NotNull Python3Parser.ClassdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitExprlist(@NotNull Python3Parser.ExprlistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitSmall_stmt(@NotNull Python3Parser.Small_stmtContext ctx) {
		if ( ctx.global_stmt() != null ) {
			return visitGlobal_stmt(ctx.global_stmt());
		}
		return null;
	}
	
	@Override
	public Statement visitTrailer(@NotNull Python3Parser.TrailerContext ctx) {
		return visitArglist(ctx.arglist());
	}
	
	@Override
	public Statement visitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx) {
		if ( ctx.term().size() == 1 ) {
			return visitTerm(ctx.term(0));
		}
		ExpressionTree tree = new ExpressionTree(ctx.opss.get(0).getText());
		tree.addChild((ExpressionTree) visitTerm(ctx.term(0)));
		for (int i = 1; i < ctx.opss.size(); i++) {
			ExpressionTree tree1 = new ExpressionTree(ctx.opss.get(i).getText());
			tree1.addChild((ExpressionTree) visitTerm(ctx.term(i + 1)));
			tree.addChild(tree1);
		}
		return tree;
	}
	
	@Override
	public Statement visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
		List<ArgumentStatement> statements = new ArrayList<>();
		for (int i = 0; i < ctx.argument().size(); i++) {
			statements.add((ArgumentStatement) visitArgument(ctx.argument(i)));
		}
		return new ArgumentListStatement(statements);
	}
	
	@Override
	public Statement visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx) {
		List<Statement> statements = new ArrayList<>();
		for (int i = 0; i < ctx.small_stmt().size(); i++) {
			statements.add(visitSmall_stmt(ctx.small_stmt(i)));
		}
		return new StatementBlock(statements);
	}
	
	@Override
	public Statement visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx) {
		Map<String, ExpressionTree> map = new HashMap<>();
		if ( ctx.test(0) != null ) {
			map.put(ctx.tfpdef(0).NAME().getText(), (ExpressionTree) visitTest(ctx.test(0)));
		}
		for (int i = 1; i < ctx.tfpdef().size(); i++) {
			if ( i >= ctx.test().size() ) {
				map.put(ctx.tfpdef().get(i).NAME().getText(), new ExpressionTree(null));
				break;
			}
			map.put(ctx.tfpdef().get(i).NAME().getText(), (ExpressionTree) visitTest(ctx.test(i)));
		}
		return new ParametersStatement(map);
	}
	
	@Override
	public Statement visitExpr(@NotNull Python3Parser.ExprContext ctx) {
		ExpressionTree tree = new ExpressionTree("|");
		for (int i = 0; i < ctx.xor_expr().size(); i++) {
			tree.addChild((ExpressionTree) visitXor_expr(ctx.xor_expr(i)));
		}
		return tree;
	}
	
	@Override
	public Statement visitTerm(@NotNull Python3Parser.TermContext ctx) {
		if ( ctx.factor().size() == 1 ) {
			return visitFactor(ctx.factor(0));
		}
		ExpressionTree tree = new ExpressionTree(ctx.opss.get(0).getText());
		tree.addChild((ExpressionTree) visitFactor(ctx.factor(0)));
		for (int i = 1; i < ctx.opss.size(); i++) {
			ExpressionTree tree1 = new ExpressionTree(ctx.opss.get(i).getText());
			tree1.addChild((ExpressionTree) visitFactor(ctx.factor(i + 1)));
			tree.addChild(tree1);
		}
		return tree;
	}
	
	@Override
	public Statement visitPower(@NotNull Python3Parser.PowerContext ctx) {
		if(ctx.trailer().size() != 0){
			ExpressionTree tree = new ExpressionTree(null);
			ExpressionTree.Node node = new ExpressionTree.Node();
			PythonFunction function = functions.get(ctx.atom().getText());
			node.object = function;
			ArgumentListStatement argumentListStatement = (ArgumentListStatement) visitTrailer(ctx.trailer().get(0));
			tree.setRoot(node);
			function.setParameters(argumentListStatement);
			SymbolTable.getTable().beginScope(ctx.atom().getText(), SymbolTable.ScopeType.FUNCTION);
			return tree;
		}
		return visitAtom(ctx.atom());
	}
	
	@Override
	public Statement visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFactor(@NotNull Python3Parser.FactorContext ctx) {
		if ( ctx.power() != null ) {
			return visitPower(ctx.power());
		}
		ExpressionTree tree = (ExpressionTree) visitFactor(ctx.factor());
		tree.setSpeical(ctx.op.getText());
		return tree;
	}
	
	@Override
	public Statement visitSliceop(@NotNull Python3Parser.SliceopContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFuncdef(@NotNull Python3Parser.FuncdefContext ctx) {
		String name = ctx.NAME().getSymbol().getText();
		StatementBlock block = (StatementBlock) visitSuite(ctx.suite());
		ParametersStatement parameters = (ParametersStatement) visit(ctx.parameters());
		PythonFunction function = new PythonFunction(name, block, parameters);
		functions.put(function.getName(), function);
		return block;
	}
	
	@Override
	public Statement visitSubscriptlist(@NotNull Python3Parser.SubscriptlistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTest_nocond(@NotNull Python3Parser.Test_nocondContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitComp_iter(@NotNull Python3Parser.Comp_iterContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitNonlocal_stmt(@NotNull Python3Parser.Nonlocal_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitEval_input(@NotNull Python3Parser.Eval_inputContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitVfpdef(@NotNull Python3Parser.VfpdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitImport_name(@NotNull Python3Parser.Import_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitComp_if(@NotNull Python3Parser.Comp_ifContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAugassign(@NotNull Python3Parser.AugassignContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitPass_stmt(@NotNull Python3Parser.Pass_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitExpr_stmt(@NotNull Python3Parser.Expr_stmtContext ctx) {
		List<String> names = new ArrayList<>(Arrays.asList(ctx.testlist_star_expr(0).getText().split(",")));
		TestListStatement statement = (TestListStatement) visitTestlist_star_expr(ctx.testlist_star_expr(1));
		if(ctx.augassign() != null)
			return new AssignStatement(names, statement.getTrees());
		return new AugAssignStatement(ctx.augassign().getText(), names, statement.getTrees());
	}
	
	@Override
	public Statement visitProg(@NotNull Python3Parser.ProgContext ctx) {
		return new StatementBlock(ctx.stmt().stream().map(this::visitStmt).collect(Collectors.toList()));
	}
	
	@Override
	public Statement visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitSuite(@NotNull Python3Parser.SuiteContext ctx) {
		if(ctx.simple_stmt() != null){
			return new StatementBlock(new ArrayList<>(Collections.singletonList(visitSimple_stmt(ctx.simple_stmt()))));
		}
		List<Statement> statements = new ArrayList<>();
		for (Python3Parser.StmtContext context : ctx.stmt()) {
			statements.add(visitStmt(context));
		}
		return new StatementBlock(statements);
	}
	
	@Override
	public Statement visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx) {
		return new ContinueStatement();
	}
	
	@Override
	public Statement visitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx) {
		List<ExpressionTree> trees = new ArrayList<>();
		ctx.test().forEach(testContext -> trees.add((ExpressionTree) visitTest(testContext)));
		return new TestListStatement(trees);
	}
	
	@Override
	public Statement visitVarargslist(@NotNull Python3Parser.VarargslistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx) {
		TestListStatement testListStatement = (TestListStatement) visitTestlist(ctx.testlist());
		StatementBlock body = (StatementBlock) visitSuite(ctx.suite(0));
		return new ForStatement(new ArrayList<>(Arrays.asList(ctx.exprlist().getText().split(","))), testListStatement.getTrees(), body);
	}
	
	@Override
	public Statement visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx) {
		ExpressionTree tree = (ExpressionTree) visitExprlist(ctx.exprlist());
		return new DeleteStatement(tree);
	}
	
	@Override
	public Statement visitAtom(@NotNull Python3Parser.AtomContext ctx) {
		if ( ctx.FALSE() != null ) {
			ExpressionTree tree = new ExpressionTree(null);
			ExpressionTree.Node node = new ExpressionTree.Node();
			node.object = new PythonBoolean(false);
			tree.setRoot(node);
			return tree;
		}
		if ( ctx.TRUE() != null ) {
			ExpressionTree tree = new ExpressionTree(null);
			ExpressionTree.Node node = new ExpressionTree.Node();
			node.object = new PythonBoolean(true);
			tree.setRoot(node);
			return tree;
		}
		if ( ctx.NONE() != null ) {
			return null;
		}
		if ( ctx.NAME() != null ) {
			return SymbolTable.getTable().lookup(ctx.NAME().getText());
		}
		if ( ctx.number() != null ) {
			return visitNumber(ctx.number());
		}
		return visitString(ctx.string(0));
	}
	
	@Override
	public Statement visitStmt(@NotNull Python3Parser.StmtContext ctx) {
		if ( ctx.compound_stmt() != null ) {
			return visitSimple_stmt(ctx.simple_stmt());
		}
		else {
			return visitCompound_stmt(ctx.compound_stmt());
		}
	}
	
	@Override
	public Statement visit(@NotNull ParseTree tree) {
		return tree.accept(this);
	}
	
	@Override
	public Statement visitChildren(@NotNull RuleNode node) {
		return null;
	}
	
	@Override
	public Statement visitTerminal(@NotNull TerminalNode node) {
		return null;
	}
	
	@Override
	public Statement visitErrorNode(@NotNull ErrorNode node) {
		return null;
	}
}