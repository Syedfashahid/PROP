package prop.assignment0.node;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class FactorNode implements INode {
	private Lexeme lex;
	private ExpressionNode expr;
	
	public FactorNode(Lexeme lex) {
		this.lex = lex;
	}
	
	public FactorNode(ExpressionNode expr) {
		this.expr = expr;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "FactorNode");
		
		if(lex != null)
			INode.appendString(builder, tabs + 1, lex.toString());
		else {
			INode.appendString(builder, tabs + 1, new Lexeme('(', Token.LEFT_PAREN).toString());
			expr.buildString(builder, tabs + 1);
			INode.appendString(builder, tabs + 1, new Lexeme(')', Token.RIGHT_PAREN).toString());
		}
	}
}