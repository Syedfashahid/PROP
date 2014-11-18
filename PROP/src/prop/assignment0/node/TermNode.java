package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;

public class TermNode implements INode {
	private FactorNode factor;
	private TermNode term;
	private Lexeme op;
	
	public TermNode(FactorNode factor, TermNode term, Lexeme op) {
		this.factor = factor;
		this.term = term;
		this.op = op;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(term == null)
			return factor.evaluate(args);
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		appendTabs(builder, tabs);
		builder.append("TermNode\n");
		factor.buildString(builder, tabs + 1);
		
		if(op != null) {
			appendTabs(builder, tabs);
			builder.append("\t" + op + "\n");
		}
		
		if(term != null)
			term.buildString(builder, tabs + 1);
	}
	
	public void appendTabs(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
	}
}