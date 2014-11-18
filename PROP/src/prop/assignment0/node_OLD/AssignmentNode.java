package prop.assignment0.node_OLD;

import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;

public class AssignmentNode implements INode {
	private Lexeme id;
	private ExpressionNode expr;
	private static final Lexeme OP = new Lexeme('=', Token.ASSIGN_OP);
	private static final Lexeme EOS = new Lexeme(';', Token.SEMICOLON);
	
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
		appendTabs(builder, tabs);
		builder.append("AssignmentNode\n");
		appendTabs(builder, tabs);
		builder.append("\t" + id + "\n");
		appendTabs(builder, tabs);
		builder.append("\t" + OP + "\n");
		expr.buildString(builder, tabs + 1);
		appendTabs(builder, tabs);
		builder.append("\t" + EOS + "\n");
	}
	
	public void appendTabs(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
	}
	
	@Override
	public String toString() {
		return "AssignmentNode " + id + " " + expr + " " + OP;
	}
}