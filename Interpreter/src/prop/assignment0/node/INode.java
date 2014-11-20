package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public interface INode {
	/**
	 * The argument array 'args' is only needed for the requirements for grade A and B.
	 * When not needed just call evaluate with null as the actual parameter.
	 */
	Object evaluate(Object[] args) throws Exception; 
	
	void buildString(StringBuilder builder, int tabs);
	
	public static void appendString(StringBuilder builder, int tabs, String str) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append(str + "\n");
	}
	
	public static Double objectToDouble(Object obj, HashMap<Character, Double> map) throws Exception {
		Double ret = Double.MAX_VALUE;
		
		if(obj instanceof Double) {
			ret = (Double) obj;
			return ret;
		}
		
		if(obj instanceof Lexeme) {
			Lexeme tmp = (Lexeme) obj;
			if(((Lexeme) obj).token() == Token.IDENT) {
				if(map.containsKey(((String) tmp.value()).charAt(0)))
					return map.get(((String) tmp.value()).charAt(0));
				else if(((String) tmp.value()).charAt(0) != 'a')
					throw new Exception("Undefined variable " + ((String) tmp.value()).charAt(0));
			}
		}
		
		//if(obj instanceof Character) {
		//	return 1.0;
		//}
		
		return Double.parseDouble(((Lexeme) obj).value().toString());
	}
	
	public static Object calculate(Object[] args, Token opOne, Token opTwo, Object lastArg) throws Exception {
		HashMap<Character, Double> map = null;
		
		for(int i = 0; i < args.length - 2; i += 2) {
			if(args[0] instanceof HashMap) {
				map = (HashMap<Character, Double>) args[0];
				continue;
			}
			
			Double dblOne = INode.objectToDouble(args[i], map);
			Lexeme oper = (Lexeme) args[i + 1];
			Double dblTwo = INode.objectToDouble(args[i + 2], map);
			
			if(oper.token() == opOne && opOne == Token.ADD_OP)
				args[i + 2] = dblOne + dblTwo;
			else if(oper.token() == opTwo && opTwo == Token.SUB_OP)
				args[i + 2] = dblOne - dblTwo;
			else if(oper.token() == opOne && opOne == Token.DIV_OP)
				args[i + 2] = dblOne / dblTwo;
			else if(oper.token() == opTwo && opTwo == Token.MULT_OP)
				args[i + 2] = dblOne * dblTwo;
			else
				throw new Exception("Expected ADD_OP, SUB_OP, DIV_OP or MULT_OP but was " + oper.token());
		}
		
		Double dblOne = INode.objectToDouble(args[args.length - 2], map);
		Lexeme oper = (Lexeme) args[args.length - 1];
		Double dblTwo = INode.objectToDouble(lastArg, map);
		
		if(oper.token() == opOne && opOne == Token.ADD_OP)
			return dblOne + dblTwo;
		else if(oper.token() == opTwo && opTwo == Token.SUB_OP)
			return dblOne - dblTwo;
		else if(oper.token() == opOne && opOne == Token.DIV_OP)
			return dblOne / dblTwo;
		else if(oper.token() == opTwo && opTwo == Token.MULT_OP)
			return dblOne * dblTwo;
		else
			throw new Exception("Expected ADD_OP, SUB_OP, DIV_OP or MULT_OP but was " + oper.token());
	}
	
	public static Object[] getNewArgs(Object[] args, Object eval, Lexeme oper) {
		Object[] newArgs = null;
		
		if(args != null) {
			newArgs = new Object[args.length + 2];
		
			for(int i = 0; i < args.length; i++)
				newArgs[i] = args[i];
		}
		
		if(newArgs == null)
			newArgs = new Object[2];
		
		newArgs[newArgs.length - 2] = eval;
		newArgs[newArgs.length - 1] = oper;
		
		for(int i = 0; i < newArgs.length; i++)
			if(newArgs[i] instanceof Lexeme) {
				Lexeme tmp = (Lexeme) newArgs[i];
				
				if(tmp.token() == Token.SEMICOLON)
					System.out.println("hello");
			}
		
		return newArgs;
	}
}