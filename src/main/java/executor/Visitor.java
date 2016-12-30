package executor;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import python.object.PythonBlock;
import python.object.PythonFunction;
import python.object.PythonParameters;
import python.scope.ConditionStatement;
import python.statement.Statement;

import java.util.HashMap;
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
	public Statement visitNot_test(@NotNull Python3Parser.Not_testContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitFile_input(@NotNull Python3Parser.File_inputContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitNormalAssign(@NotNull Python3Parser.NormalAssignContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitXor_expr(@NotNull Python3Parser.Xor_exprContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitNumber(@NotNull Python3Parser.NumberContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAnd_expr(@NotNull Python3Parser.And_exprContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitOr_test(@NotNull Python3Parser.Or_testContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitComparison(@NotNull Python3Parser.ComparisonContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTest(@NotNull Python3Parser.TestContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitLambdef(@NotNull Python3Parser.LambdefContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitWhatever(@NotNull Python3Parser.WhateverContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitAnd_test(@NotNull Python3Parser.And_testContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitGlobal_stmt(@NotNull Python3Parser.Global_stmtContext ctx) {
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitStar_expr(@NotNull Python3Parser.Star_exprContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitBreak_stmt(@NotNull Python3Parser.Break_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitArgumentAssign(@NotNull Python3Parser.ArgumentAssignContext ctx) {
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
		ConditionStatement condition = (ConditionStatement) visit(ctx.test(0));
		if((Boolean) condition.run()){
			return visit(ctx.suite(0));
		}
		
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
		return null;
	}
	
	@Override
	public Statement visitArglist(@NotNull Python3Parser.ArglistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitSimple_stmt(@NotNull Python3Parser.Simple_stmtContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTypedargslist(@NotNull Python3Parser.TypedargslistContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitExpr(@NotNull Python3Parser.ExprContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitTerm(@NotNull Python3Parser.TermContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitPower(@NotNull Python3Parser.PowerContext ctx) {
		return null;
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
		PythonBlock block = (PythonBlock) visit(ctx.suite());
		PythonParameters parameters = (PythonParameters) visit(ctx.parameters());
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
		return null;
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
		return null;
	}
	
	@Override
	public Statement visitAtom(@NotNull Python3Parser.AtomContext ctx) {
		return null;
	}
	
	@Override
	public Statement visitStmt(@NotNull Python3Parser.StmtContext ctx) {
		return null;
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