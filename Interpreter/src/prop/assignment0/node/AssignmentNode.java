package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class AssignmentNode implements INode {
	private Lexeme id;
	private ExpressionNode expr;
	
	public AssignmentNode(Lexeme id, ExpressionNode expr) {
		this.id = id;
		this.expr = expr;
	}
	
	/**
	 * Evaluates expr and saves the result in a Object array then returns it.
	 */
	@Override
	public Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception {
		Object exprEval = expr.evaluate(null, map);
		
		Object[] tmp = new Object[3];
		tmp[0] = id.value();
		tmp[1] = '=';
		tmp[2] = exprEval;
		
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