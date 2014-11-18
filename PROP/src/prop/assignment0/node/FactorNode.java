package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;

public class FactorNode implements INode {
	private Lexeme integer;
	private ExpressionNode expr;
	private Lexeme leftParen;
	private Lexeme rightParen;
	
	public FactorNode(Lexeme integer, ExpressionNode expr) {
		this.integer = integer;
		this.expr = expr;
		
		if(expr != null) {
			leftParen = new Lexeme('(', Token.LEFT_PAREN);
			rightParen = new Lexeme(')', Token.RIGHT_PAREN);
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(expr == null)
			return integer;
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		appendTabs(builder, tabs);
		builder.append("FactorNode\n");
		
		if(integer != null) {
			appendTabs(builder, tabs);	
			builder.append("\t" + integer +"\n");
		}
		
		if(expr != null) {
			appendTabs(builder, tabs);
			builder.append("\t" + leftParen + "\n");	
			expr.buildString(builder, tabs + 1);
			appendTabs(builder, tabs);
			builder.append("\t" + rightParen + "\n");
		}
	}
	
	public void appendTabs(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
	}
}