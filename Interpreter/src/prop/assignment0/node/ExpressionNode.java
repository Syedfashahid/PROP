package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class ExpressionNode implements INode {
	private TermNode term;
	private ExpressionNode expr;
	private Lexeme oper;

	public ExpressionNode(TermNode term) {
		this.term = term;
	}

	public ExpressionNode(TermNode term, ExpressionNode expr, Lexeme oper) {
		this.term = term;
		this.expr = expr;
		this.oper = oper;
	}
	
	/**
	 * Begins with evaluating it's term.
	 * if expr is null and there are no arguments, just return the result of terms evaluation.
	 * else, calculate the value of Object[] args contents sending ADD_OP and SUB_OP as operators, etc.
	 * if expr is not null, evalute it while also sending arguments via getNewArgs.
	 */
	@Override
	public Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception {
		Object termEval = term.evaluate(null, map);

		if(expr == null)
			if(args == null)
				return termEval;
			else
				return INode.calculate(args, Token.ADD_OP, Token.SUB_OP, termEval, map);
		
		return expr.evaluate(INode.getNewArgs(args, termEval, oper), map);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "ExpressionNode");
		term.buildString(builder, tabs + 1);

		if(expr != null) {
			INode.appendString(builder, tabs + 1, oper.toString());
			expr.buildString(builder, tabs + 1);
		}
	}
}