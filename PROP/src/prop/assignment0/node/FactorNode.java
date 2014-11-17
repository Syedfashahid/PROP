package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;

public class FactorNode implements INode {
	public Lexeme integer;
	public ExpressionNode expr;
	
	public FactorNode() {
		
	}
	
	public FactorNode(Lexeme integer, ExpressionNode expr) {
		this.integer = integer;
		this.expr = expr;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

	}
	
	@Override
	public String toString() {
		String str = "FactorNode";
		
		if(integer != null)
			str += "\n\t" + integer;
		if(expr != null)
			str += "\n\t" + expr;
		
		return str;
	}
}