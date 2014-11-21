package prop.assignment0.node;

import java.util.HashMap;

import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;

public interface INode {
	/**
	 * Change: Added a HashMap of variables as keys and their double value as value.
	 * chose to seperate those from the args array for convenience reasons.
	 */
	Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception; 
	void buildString(StringBuilder builder, int tabs);
	
	/**
	 * Appends a number of tabs to a StringBuilder followed by a String.
	 */
	public static void appendString(StringBuilder builder, int tabs, String str) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append(str + "\n");
	}
	
	/**
	 * Converts an Object to a Double, either by casting it or looking it up in the HashMap if it's a variable.
	 */
	public static Double objectToDouble(Object obj, HashMap<String, Double> map) {
		if(obj instanceof Double)
			return (Double) obj;
		
		if(obj instanceof String)
			return map.get(obj);
		
		return Double.parseDouble(((Lexeme) obj).value().toString());
	}
	
	/**
	 * Method for calculating the double value of a expression or term.
	 * Adds arguments until it reaches a ExpressionNode where expr is null (or TermNode where term is null).
	 * Iterates over the argument list, identifying two doubles and which operator should be used.
	 * Saves the results at index + 2 and continues iterating until the result of the caluclation is at args.length - 2
	 * and the next operator to be used is at args.length - 1. These two are then used together with the ExpressionNodes or TermNodes
	 * own Term or Factor evaluation to return the final result.
	 */
	public static Object calculate(Object[] args, Token opOne, Token opTwo, Object lastArg, HashMap<String, Double> map) throws Exception {
		for(int i = 0; i < args.length - 2; i += 2) {
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
	
	/**
	 * Used to copy all the old arguments to a new array and add the calling class own arguments to the array.
	 */
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
		
		return newArgs;
	}
}