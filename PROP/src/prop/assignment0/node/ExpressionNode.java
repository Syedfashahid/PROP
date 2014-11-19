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

	/*@Override
	public Object evaluate(Object[] args) throws Exception {	
		if(expr == null) {
			//if(args == null || args.length == 0)
				return term.evaluate(args);

			/*Lexeme[] lexArr = (Lexeme[]) args;

			for(int i = 0; i < lexArr.length - 2; i+=2) {
				Lexeme lexOne = lexArr[i];
				Lexeme operator = lexArr[i + 1];
				Lexeme lexTwo = lexArr[i + 2];
				
				double doubleOne = (Double) lexOne.value();
				double doubleTwo = (Double) lexTwo.value();
				
				if(operator.token() == Token.ADD_OP)
					lexArr[i + 2] = new Lexeme(doubleOne + doubleTwo, Token.INT_LIT);
				else if(operator.token() == Token.SUB_OP)
					lexArr[i + 2] = new Lexeme(doubleOne - doubleTwo, Token.INT_LIT);
			}
			
			Lexeme lexOne = lexArr[lexArr.length - 2];
			Lexeme operator = lexArr[lexArr.length - 1];
			//Lexeme lexTwo = (Lexeme) term.evaluate(null);
			
			double doubleOne = (Double) lexOne.value();
			//double doubleTwo = (Double) lexTwo.value();
			double doubleTwo = (Double) term.evaluate(args);
			
			if(operator.token() == Token.ADD_OP)
				return doubleOne + doubleTwo;
			else if(operator.token() == Token.SUB_OP)
				return doubleOne - doubleTwo;
		} else if(expr != null) {
			if(args != null && args.length != 0) {				
				Lexeme[] lexArr = new Lexeme[args.length + 2];

				for(int i = 0; i < args.length; i++)
					lexArr[i] = (Lexeme) args[i];

				lexArr[lexArr.length - 1] = op;
				lexArr[lexArr.length - 2] = (Lexeme) term.evaluate(null);
				//lexArr[lexArr.length - 2] = new Lexeme(term.evaluate(null), Token.INT_LIT);

				return expr.evaluate(lexArr);
			} else if(args == null || args.length == 0) {
				Lexeme[] lexArr = new Lexeme[2];
				lexArr[lexArr.length - 1] = op;
				//lexArr[lexArr.length - 2] = (Lexeme) term.evaluate(null);
				lexArr[lexArr.length - 2] = new Lexeme(term.evaluate(null), Token.INT_LIT);
				
				return expr.evaluate(lexArr);
			}
		}

		throw new Exception("Something went horribly wrong...");
	}*/
	
	@Override
	public Object evaluate(Object[] args) {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		appendTabs(builder, tabs);
		builder.append("ExpressionNode\n");
		term.buildString(builder, tabs + 1);

		if(op != null) {
			appendTabs(builder, tabs);
			builder.append("\t" + op + "\n");
		}

		if(expr != null)
			expr.buildString(builder, tabs + 1);
	}

	public void appendTabs(StringBuilder builder, int tabs) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
	}
}