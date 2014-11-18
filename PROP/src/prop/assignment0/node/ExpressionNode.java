package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;

public class ExpressionNode implements INode {
	private TermNode term;
	private ExpressionNode expr;
	private Lexeme op;

	public ExpressionNode(TermNode term, ExpressionNode expr, Lexeme op) {
		this.term = term;
		this.expr = expr; 
		this.op = op;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {	
		if(expr == null)
			return term.evaluate(args);
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		appendTabs(builder, tabs);
		builder.append("ExpressionNode\n");
		term.buildString(builder, tabs + 1);
		
		if(op != null) {
			appendTabs(builder, tabs);
			builder.append("\t" + op + "\n");
		}

		if(expr != null)
			expr.buildString(builder, tabs + 1);
	}
	
	public void appendTabs(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
	}
}