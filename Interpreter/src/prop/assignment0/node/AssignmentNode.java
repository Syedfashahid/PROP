package prop.assignment0.node;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class AssignmentNode implements INode {
	private Lexeme id;
	private ExpressionNode expr;
	
	public AssignmentNode(Lexeme id, ExpressionNode expr) {
		this.id = id;
		this.expr = expr;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		Object[] tmp = new Object[3];
		tmp[0] = ((String) id.value()).charAt(0);
		tmp[1] = '=';
		tmp[2] = expr.evaluate(args);
		
		return tmp;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "AssignmentNode");
		INode.appendString(builder, tabs + 1, id.toString());
		INode.appendString(builder, tabs + 1, new Lexeme('=', Token.ASSIGN_OP).toString());
		expr.buildString(builder, tabs + 1);
		INode.appendString(builder, tabs + 1, new Lexeme(';', Token.SEMICOLON).toString());
	}
}