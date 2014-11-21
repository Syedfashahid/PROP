package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class TermNode implements INode {
	private FactorNode factor;
	private TermNode term;
	private Lexeme oper;
	
	public TermNode(FactorNode factor) {
		this.factor = factor;
	}
	
	public TermNode(FactorNode factor, TermNode term, Lexeme oper) {
		this.factor = factor;
		this.term = term;
		this.oper = oper;
	}
	
	/**
	 * Works pretty much exactly the same as ExpressionNode.evaluate() but this time we use DIV_OP and MULT_OP instead.
	 */
	@Override
	public Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception {
		Object factEval = factor.evaluate(null, null);
		
		if(term == null)
			if(args == null)
				return factEval;
			else
				return INode.calculate(args, Token.DIV_OP, Token.MULT_OP, factEval, map);
		
		return term.evaluate(INode.getNewArgs(args, factEval, oper), map);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "TermNode");
		factor.buildString(builder, tabs + 1);
		
		if(term != null) {
			INode.appendString(builder, tabs + 1, oper.toString());
			term.buildString(builder, tabs + 1);
		}
	}
}