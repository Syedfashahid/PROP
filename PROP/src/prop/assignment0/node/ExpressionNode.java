package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;

public class ExpressionNode implements INode {
	private TermNode term;
	private ExpressionNode expr;
	private Lexeme op;
	
	public ExpressionNode(TermNode term, ExpressionNode expr, Lexeme op) {
		this.term = term;
		this.expr = expr; 
		this.op = op;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if(expr != null) {
			Object[] tmp = {term.evaluate(args), op};
			return expr.evaluate(tmp);
		}
		
		if(args != null) {
			Lexeme tmp = (Lexeme) args[0];
			int i = Integer.parseInt((String) tmp.value());
			Lexeme tmp2 = (Lexeme) term.evaluate(args);
			int i2 = Integer.parseInt((String) tmp2.value());
			//int i2 = (int) term.evaluate(args);
			Lexeme argOp = (Lexeme) args[args.length - 1];
			
			if(argOp.token() == Token.SUB_OP)
				return i - i2;
			
			if(argOp.token() == Token.ADD_OP)
				return i + i2;
		}
		
		return term.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append("ExpressionNode\n");
		term.buildString(builder, tabs + 1);
		
		
		
		if(op != null) {
			for(int i = 0; i < tabs; i++)
				builder.append("\t");
			
			builder.append("\t" + op + "\n");
		}
		
		if(expr != null) {
			//for(int i = 0; i < tabs; i++)
				//builder.append("\t");
			
			expr.buildString(builder, tabs + 1);
		}
	}
	
	@Override
	public String toString() {
		return "ExpressionNode " + term + " " + expr + " " + op;
	}
}