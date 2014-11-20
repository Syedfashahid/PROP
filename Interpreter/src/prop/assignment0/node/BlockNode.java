package prop.assignment0.node;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public class BlockNode implements INode {
	private StatementsNode stmts;
	
	public BlockNode(StatementsNode stmts) {
		this.stmts = stmts;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return stmts.evaluate(null);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "BlockNode");
		INode.appendString(builder, tabs, new Lexeme('{', Token.LEFT_CURLY).toString());
		stmts.buildString(builder, tabs + 1);
		INode.appendString(builder, tabs, new Lexeme('}', Token.RIGHT_CURLY).toString());
	}
}