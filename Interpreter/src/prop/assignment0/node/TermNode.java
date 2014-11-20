package prop.assignment0.node;

import prop.assignment0.lexeme.Lexeme;

public class TermNode implements INode {
	private FactorNode factor;
	private TermNode term;
	private Lexeme oper;
	
	public TermNode(FactorNode factor) {
		this.factor = factor;
	}
	
	public TermNode(FactorNode factor, TermNode term, Lexeme oper) {
		this.factor = factor;
		this.term = term;
		this.oper = oper;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "TermNode");
		factor.buildString(builder, tabs + 1);
		
		if(term != null) {
			INode.appendString(builder, tabs + 1, oper.toString());
			term.buildString(builder, tabs + 1);
		}
	}
}