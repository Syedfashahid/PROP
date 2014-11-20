package prop.assignment0.node;

import prop.assignment0.lexeme.Lexeme;

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
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
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