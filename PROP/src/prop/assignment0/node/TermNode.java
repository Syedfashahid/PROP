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
		if(term == null) {
			if(args == null || args.length == 0)
				return factor.evaluate(null);
			
			Lexeme[] lexArr = (Lexeme[]) args;

			for(int i = 0; i < lexArr.length - 2; i+=2) {
				Lexeme lexOne = lexArr[i];
				Lexeme operator = lexArr[i + 1];
				Lexeme lexTwo = lexArr[i + 2];
				
				double doubleOne = (Double) lexOne.value();
				double doubleTwo = (Double) lexTwo.value();
				
				if(operator.token() == Token.DIV_OP)
					lexArr[i + 2] = new Lexeme(doubleOne * doubleTwo, Token.INT_LIT);
				else if(operator.token() == Token.MULT_OP)
					lexArr[i + 2] = new Lexeme(doubleOne / doubleTwo, Token.INT_LIT);
				else if(operator.token() == Token.ADD_OP)
					lexArr[i + 2] = new Lexeme(doubleOne + doubleTwo, Token.INT_LIT);
				else if(operator.token() == Token.SUB_OP)
					lexArr[i + 2] = new Lexeme(doubleOne - doubleTwo, Token.INT_LIT);
			}
			
			Lexeme lexOne = lexArr[lexArr.length - 2];
			Lexeme operator = lexArr[lexArr.length - 1];
			Lexeme lexTwo = (Lexeme) factor.evaluate(null);
			
			double doubleOne = (Double) lexOne.value();
			double doubleTwo = (Double) lexTwo.value();
			
			if(operator.token() == Token.DIV_OP)
				return doubleOne / doubleTwo;
			else if(operator.token() == Token.MULT_OP)
				return doubleOne * doubleTwo;
			else if(operator.token() == Token.ADD_OP)
				return doubleOne + doubleTwo;
			else if(operator.token() == Token.SUB_OP)
				return doubleOne - doubleTwo;
		} else if(term != null) {
			if(args != null && args.length != 0) {				
				Lexeme[] lexArr = new Lexeme[args.length + 2];

				for(int i = 0; i < args.length; i++)
					lexArr[i] = (Lexeme) args[i];

				lexArr[lexArr.length - 1] = op;
				lexArr[lexArr.length - 2] = (Lexeme) factor.evaluate(null);

				return term.evaluate(lexArr);
			} else if(args == null || args.length == 0) {
				Lexeme[] lexArr = new Lexeme[2];
				lexArr[lexArr.length - 1] = op;
				lexArr[lexArr.length - 2] = (Lexeme) factor.evaluate(null);
				
				return term.evaluate(lexArr);
			}
		}

		throw new Exception("Something went horribly wrong...");
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