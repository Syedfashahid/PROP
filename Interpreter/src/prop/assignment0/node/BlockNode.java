package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class BlockNode implements INode {
	private StatementsNode stmts;
	
	public BlockNode(StatementsNode stmts) {
		this.stmts = stmts;
	}
	
	/**
	 * returns the result of stmts evaluation.
	 */
	@Override
	public Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception {
		return stmts.evaluate(null, map);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "BlockNode");
		INode.appendString(builder, tabs, new Lexeme('{', Token.LEFT_CURLY).toString());
		stmts.buildString(builder, tabs + 1);
		INode.appendString(builder, tabs, new Lexeme('}', Token.RIGHT_CURLY).toString());
	}
}