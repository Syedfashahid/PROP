package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;

public class AssignmentNode implements INode {
	public Lexeme id;
	public ExpressionNode expr;
	public static final Lexeme OP = new Lexeme('=', Token.ASSIGN_OP);
	public static final Lexeme EOS = new Lexeme(';', Token.SEMICOLON);
	
	public AssignmentNode(Lexeme id, ExpressionNode expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return expr.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("AssignmentNode\n");
		
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("\t" + id + "\n");
		
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("\t" + OP + "\n");
		expr.buildString(builder, tabs + 1);
		
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("\t" + EOS + "\n");
	}
	
	@Override
	public String toString() {
		return "AssignmentNode " + id + " " + expr + " " + OP;
	}
}