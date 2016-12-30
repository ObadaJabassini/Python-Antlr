package executor;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import python.object.*;
import python.scope.SymbolTable;
import python.statement.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visitor implements Python3Visitor<Statement>
{
	private Map<String, PythonFunction> functions = new HashMap<>();
	@Override
	public Statement visitTestlist(@NotNull Python3Parser.TestlistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAssert_stmt(@NotNull Python3Parser.Assert_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitArgument(@NotNull Python3Parser.ArgumentContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitNot_test(@NotNull Python3Parser.Not_testContext ctx) {
		if(ctx.NOT().getText().equals(""))
			return visitComparison(ctx.comparison());
		ComparisonStatement statement = (ComparisonStatement) visitNot_test(ctx.not_test());
		statement.setNot(true);
		return statement;
	}
	
	@Override
	public Statement visitFile_input(@NotNull Python3Parser.File_inputContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx) {
		if(ctx.and_expr().size() == 1)
			return visitAnd_expr(ctx.and_expr(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitAnd_expr(ctx.and_expr(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.and_expr().size(); j++) {
			ops.add("^");
			statement = (ExpressionStatement) visitAnd_expr(ctx.and_expr(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
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
		return null;
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
		if(!ctx.if_stmt().getText().equals("")){
			return visitIf_stmt(ctx.if_stmt());
		}
		if(!ctx.while_stmt().getText().equals("")){
			return visitWhile_stmt(ctx.while_stmt());
		}
		if(!ctx.for_stmt().getText().equals("")){
			return visitFor_stmt(ctx.for_stmt());
		}
		if(!ctx.try_stmt().getText().equals("")){
			return visitTry_stmt(ctx.try_stmt());
		}
		if(!ctx.classdef().getText().equals("")){
			return visitClassdef(ctx.classdef());
		}
		if(!ctx.funcdef().getText().equals("")){
			return visitFuncdef(ctx.funcdef());
		}
		if(!ctx.with_stmt().getText().equals("")){
			return visitWith_stmt(ctx.with_stmt());
		}
		if(!ctx.decorated().getText().equals("")){
			return visitDecorated(ctx.decorated());
		}
		return null;
	}
	
	@Override
	public Statement visitNumber(@NotNull Python3Parser.NumberContext ctx) {
		if(!ctx.integer().getText().equals("")){
			return new ExpressionStatement(new PythonInteger(ctx.integer().getText()));
		}
		if(!ctx.FLOAT_NUMBER().getText().equals("")){
			return new ExpressionStatement(new PythonFloat(ctx.FLOAT_NUMBER().getText()));
		}
		return new ExpressionStatement(new PythonComplex(ctx.IMAG_NUMBER().getText()));
	}
	
	@Override
	public Statement visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx) {
		if(ctx.shift_expr().size() == 1)
			return visitShift_expr(ctx.shift_expr(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitShift_expr(ctx.shift_expr(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.shift_expr().size(); j++) {
			ops.add("&");
			statement = (ExpressionStatement) visitShift_expr(ctx.shift_expr(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
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
		return null;
	}
	
	@Override
	public Statement visitDotted_name(@NotNull Python3Parser.Dotted_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFlow_stmt(@NotNull Python3Parser.Flow_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitWhile_stmt(@NotNull Python3Parser.While_stmtContext ctx) {
		TestStatement conditionStatement = (TestStatement) visitTest(ctx.test());
		StatementBlock statementBlock = (StatementBlock) visitSuite(ctx.suite(0));
		new WhileStatement(conditionStatement, statementBlock).run();
		return null;
	}
	
	@Override
	public Statement visitOr_test(@NotNull Python3Parser.Or_testContext ctx) {
		if(ctx.OR().size() == 0){
			return visitAnd_test(ctx.and_test(0));
		}
		List<String> ops = new ArrayList<>();
		List<ComparisonStatement> statements = new ArrayList<>();
		TestStatement testStatement = (TestStatement) visitAnd_test(ctx.and_test(0));
		for (int i = 0; i < testStatement.getStatements().size(); i++) {
			statements.add(testStatement.getStatements().get(i));
			ops.add(testStatement.getOperators().get(i));
		}
		ops.add("||");
		for (int i = 0; i < ctx.OR().size(); i++) {
			testStatement = (TestStatement) visitAnd_test(ctx.and_test(i));
			for (int j = 0; j < testStatement.getStatements().size(); j++) {
				statements.add(testStatement.getStatements().get(j));
				ops.add(testStatement.getOperators().get(j));
			}
			ops.add("||");
		}
		return new TestStatement(ops, statements);
	}
	
	@Override
	public Statement visitComparison(@NotNull Python3Parser.ComparisonContext ctx) {
		if(ctx.comp_op().size() == 0)
			return new ComparisonStatement(new ArrayList<String>(){{add("");}}, new ArrayList<ExpressionStatement>(){{add((ExpressionStatement) visitStar_expr(ctx.star_expr(0)));}});
		List<String> ops = new ArrayList<>();
		List<ExpressionStatement> statements = new ArrayList<>();
		statements.add((ExpressionStatement) visitStar_expr(ctx.star_expr(0)));
		for (int i = 0; i < ctx.comp_op().size(); i++) {
			ops.add(ctx.comp_op(i).getText());
			statements.add((ExpressionStatement) visitStar_expr(ctx.star_expr(i + 1)));
		}
		return new ComparisonStatement(ops, statements);
	}
	
	@Override
	public Statement visitTest(@NotNull Python3Parser.TestContext ctx) {
		return visitOr_test(ctx.or_test(0));
	}
	
	@Override
	public Statement visitSubscript(@NotNull Python3Parser.SubscriptContext ctx) {
		return null;
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
		if(ctx.arith_expr().size() == 1)
			return visitArith_expr(ctx.arith_expr(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitArith_expr(ctx.arith_expr(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.arith_expr().size(); j++) {
			ops.add(ctx.opss.get(j).getText());
			statement = (ExpressionStatement) visitArith_expr(ctx.arith_expr(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
	}
	
	@Override
	public Statement visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAnd_test(@NotNull Python3Parser.And_testContext ctx) {
		if(ctx.AND().size() == 0)
			return visit(ctx.not_test(0));
		List<String> ops = new ArrayList<>();
		List<ComparisonStatement> statements = new ArrayList<>();
		statements.add((ComparisonStatement) visitNot_test(ctx.not_test(0)));
		for (int i = 0; i < ctx.AND().size(); i++) {
			ops.add("&&");
			statements.add((ComparisonStatement) visitNot_test(ctx.not_test(i + 1)));
		}
		return new TestStatement(ops, statements);
	}
	
	@Override
	public Statement visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
		for (int i = 0; i < ctx.NAME().size(); i++) {
			SymbolTable.getTable().toGlobal(ctx.NAME(i).getText());
		}
		return Python.nil();
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
		return null;
	}
	
	@Override
	public Statement visitComp_op(@NotNull Python3Parser.Comp_opContext ctx) {
		return Python.nil();
	}
	
	@Override
	public Statement visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx) {
		ExpressionStatement expressionStatement = (ExpressionStatement) visitExpr(ctx.expr());
		if(!ctx.STAR().getText().equals(""))
			expressionStatement.setStar(true);
		return expressionStatement;
	}
	
	@Override
	public Statement visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitParameters(@NotNull Python3Parser.ParametersContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitTestlist_comp(@NotNull Python3Parser.Testlist_compContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitIf_stmt(@NotNull Python3Parser.If_stmtContext ctx) {
		TestStatement condition = (TestStatement) visitTest(ctx.test(0));
		if((Boolean) condition.run()){
			return visit(ctx.suite(0));
		}
		if(ctx.elifSuite.size() > 0){
			for (int i = 0; i < ctx.elifTest.size(); i++) {
				TestStatement statement = (TestStatement) visitTest(ctx.elifTest.get(i));
				if((Boolean) statement.run()){
					Statement statement1 = visitSuite(ctx.elifSuite.get(i));
					statement1.run();
					return statement1;
				}
			}
		}
		if(!ctx.ELSE().getText().equals("")){
			Statement stat = visitSuite(ctx.elseSuite);
			stat.run();
			return stat;
		}
		return Python.nil();
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
		if(!ctx.global_stmt().getText().equals("")){
			return visitGlobal_stmt(ctx.global_stmt());
		}
		return null;
	}
	
	@Override
	public Statement visitTrailer(@NotNull Python3Parser.TrailerContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitDotted_as_names(@NotNull Python3Parser.Dotted_as_namesContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitArith_expr(@NotNull Python3Parser.Arith_exprContext ctx) {
		if(ctx.term().size() == 1)
			return visitTerm(ctx.term(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitTerm(ctx.term(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.term().size(); j++) {
			ops.add(ctx.opss.get(j).getText());
			statement = (ExpressionStatement) visitTerm(ctx.term(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
	}
	
	@Override
	public Statement visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitExpr(@NotNull Python3Parser.ExprContext ctx) {
		if(ctx.xor_expr().size() == 1)
			return visitXor_expr(ctx.xor_expr(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitXor_expr(ctx.xor_expr(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.xor_expr().size(); j++) {
			ops.add("|");
			statement = (ExpressionStatement) visitXor_expr(ctx.xor_expr(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
	}
	
	@Override
	public Statement visitTerm(@NotNull Python3Parser.TermContext ctx) {
		if(ctx.factor().size() == 1)
			return visitFactor(ctx.factor(0));
		List<String> ops = new ArrayList<>();
		List<PythonObject> pythonObjects = new ArrayList<>();
		ExpressionStatement statement = (ExpressionStatement) visitFactor(ctx.factor(0));
		for (int i = 0; i < statement.getOperators().size(); i++) {
			ops.add(statement.getOperators().get(i));
			pythonObjects.add(statement.getObjects().get(i));
		}
		for (int j = 0; j < ctx.factor().size(); j++) {
			ops.add(ctx.opss.get(j).getText());
			statement = (ExpressionStatement) visitFactor(ctx.factor(j));
			for (int i = 0; i < statement.getOperators().size(); i++) {
				ops.add(statement.getOperators().get(i));
				pythonObjects.add(statement.getObjects().get(i));
			}
		}
		return new ExpressionStatement(pythonObjects, ops);
	}
	
	@Override
	public Statement visitPower(@NotNull Python3Parser.PowerContext ctx) {
		return visitAtom(ctx.atom());
	}
	
	@Override
	public Statement visitDotted_as_name(@NotNull Python3Parser.Dotted_as_nameContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFactor(@NotNull Python3Parser.FactorContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitProg(@NotNull Python3Parser.ProgContext ctx) {
		ctx.stmt().forEach(this::visit);
		return null;
	}
	
	@Override
	public Statement visitYield_stmt(@NotNull Python3Parser.Yield_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitSuite(@NotNull Python3Parser.SuiteContext ctx) {
		List<Statement> statements = new ArrayList<>();
		for(Python3Parser.StmtContext context :ctx.stmt()){
			statements.add(visitStmt(context));
		}
		return new StatementBlock(statements);
	}
	
	@Override
	public Statement visitContinue_stmt(@NotNull Python3Parser.Continue_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTestlist_star_expr(@NotNull Python3Parser.Testlist_star_exprContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitVarargslist(@NotNull Python3Parser.VarargslistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFor_stmt(@NotNull Python3Parser.For_stmtContext ctx) {
		
		return null;
	}
	
	@Override
	public Statement visitDel_stmt(@NotNull Python3Parser.Del_stmtContext ctx) {
		ExpressionStatement expressionStatement = (ExpressionStatement) visitExprlist(ctx.exprlist());
		SymbolTable.getTable().removeVariable((String)expressionStatement.run());
		return Python.nil();
	}
	
	@Override
	public Statement visitAtom(@NotNull Python3Parser.AtomContext ctx) {
		if(!ctx.FALSE().getText().equals("")){
			return new ExpressionStatement(Python.False());
		}
		if(!ctx.TRUE().getText().equals("")){
			return new ExpressionStatement(Python.True());
		}
		if(!ctx.NAME().getText().equals("")){
			return SymbolTable.getTable().lookup(ctx.NAME().getText());
		}
		return visitNumber(ctx.number());
	}
	
	@Override
	public Statement visitStmt(@NotNull Python3Parser.StmtContext ctx) {
		Statement statement;
		if(ctx.compound_stmt().getText().equals(""))
			statement = visit(ctx.simple_stmt());
		else
			statement = visit(ctx.compound_stmt());
		return statement;
	}
	
	@Override
	public Statement visit(@NotNull ParseTree tree) {
		return null;
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