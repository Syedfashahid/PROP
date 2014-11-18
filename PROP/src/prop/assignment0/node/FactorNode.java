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
		return integer;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("FactorNode\n");
		
		if(integer != null) {
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			
			builder.append("\t" + integer +"\n");
		}
		
		if(expr != null) {
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			
			builder.append("\t" + leftParen + "\n");	
			expr.buildString(builder, tabs + 1);
			
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			
			builder.append("\t" + rightParen + "\n");
		}
	}
	
	@Override
	public String toString() {
		String str = "FactorNode ";
		
		if(integer != null)
			str += integer;
		else if(expr != null)
			str += leftParen + " " + expr + " " + rightParen;
		
		return str;
	}
}