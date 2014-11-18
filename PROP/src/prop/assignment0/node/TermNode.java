package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;

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
		if(term != null) {
			Object[] tmp = {factor.evaluate(args), op};
			return term.evaluate(tmp);
		}
		
		if(args != null) {
			Lexeme tmp = (Lexeme) args[0];
			int i = Integer.parseInt((String) tmp.value());
			Lexeme tmp2 = (Lexeme) factor.evaluate(args);
			int i2 = Integer.parseInt((String) tmp2.value());
			Lexeme argOp = (Lexeme) args[args.length - 1];
			
			if(argOp.token() == Token.DIV_OP)
				return i / i2;
			
			if(argOp.token() == Token.MULT_OP)
				return i * i2;
		}
		
		return factor.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("TermNode\n");
		factor.buildString(builder, tabs + 1);
		
		if(op != null) {
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			
			builder.append("\t" + op + "\n");
		}
		
		if(term != null)
			term.buildString(builder, tabs + 1);
	}
	
	@Override
	public String toString() {
		return "TermNode " + factor + " " + term + " " + op;
	}
}