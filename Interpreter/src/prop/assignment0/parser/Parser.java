package prop.assignment0.parser;

import java.io.IOException;

import prop.assignment0.exception.ParserException;
import prop.assignment0.exception.TokenizerException;
import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;
import prop.assignment0.node.AssignmentNode;
import prop.assignment0.node.BlockNode;
import prop.assignment0.node.ExpressionNode;
import prop.assignment0.node.FactorNode;
import prop.assignment0.node.INode;
import prop.assignment0.node.StatementsNode;
import prop.assignment0.node.TermNode;
import prop.assignment0.tokenizer.Tokenizer;

public class Parser implements IParser {
	private Tokenizer tokenizer;
	private Lexeme current;
	
	public Parser() {
		tokenizer = new Tokenizer();
	}
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer.open(fileName);
	}

	/**
	 * Starts the parsing by moving to the first Lexeme.
	 * If that Lexme is a LEFT_CURLY it will return parseBlock(),
	 * else parseStatements().
	 */
	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		current = tokenizer.current();
		
		if(current.token() == Token.LEFT_CURLY)
			return parseBlock();
		else
			return parseStatements();
	}
	
	/**
	 * Starts with parseStatements() and then checks if the block ends with Token.RIGHT_CURLY, else throws exception.
	 * returns the BlockNode.
	 */
	public BlockNode parseBlock() throws IOException, TokenizerException {
		StatementsNode stmts = parseStatements();
		
		if(current.token() != Token.RIGHT_CURLY)
			throw new TokenizerException("Expected RIGHT_CURLY but was " + current.token());
		
		return new BlockNode(stmts);
	}
	
	/**
	 * Starts by skipping LEFT_CURLY if it is the current Lexeme.
	 * If the currennt Lexme has Token.IDENT, parseAssignment().
	 * Peeks at the next Lexeme, if it is EOF or RIGHT_CURLY, stop, otherwise continue to parse statements.
	 */
	public StatementsNode parseStatements() throws IOException, TokenizerException {
		AssignmentNode assign = null;
		StatementsNode stmts = null;
		
		if(current.token() == Token.LEFT_CURLY)
			current = tokenizer.current();
		
		if(current.token() == Token.IDENT)
			assign = parseAssignment();
		
		current = tokenizer.current();
		Lexeme next = tokenizer.peek();
		
		if(next.token() != Token.EOF && next.token() != Token.RIGHT_CURLY)
			stmts = parseStatements();
		else
			stmts = new StatementsNode();
		
		if(assign == null && stmts == null)
			return new StatementsNode();
			
		return new StatementsNode(assign, stmts);
	}
	
	/**
	 * First  looks for IDENT then saves it and moves forward, looks for ASSIGN_OP, moves forward and calls parseExpression().
	 * Moves forward again and expects SEMICOLON then returns a new AssignmentNode.
	 */
	public AssignmentNode parseAssignment() throws IOException, TokenizerException {
		if(current.token() != Token.IDENT)
			throw new TokenizerException("Expected IDENT but was " + current.token());
		
		Lexeme id = current;
		ExpressionNode expr = null;
		current = tokenizer.current();
		
		if(current.token() != Token.ASSIGN_OP)
			throw new TokenizerException("Expected ASSIGN_OP but was " + current.token());
		
		expr = parseExpression();
		current = tokenizer.current();
		
		if(current.token() != Token.SEMICOLON)
			throw new TokenizerException("Expected SEMICOLON but was " + current.token());
		
		return new AssignmentNode(id, expr);
	}
	
	public ExpressionNode parseExpression() throws IOException, TokenizerException {		
		TermNode term = parseTerm();
		Lexeme next = tokenizer.peek();
		
		if(next.token() != Token.ADD_OP && next.token() != Token.SUB_OP) {
			return new ExpressionNode(term);
		}
		
		current = tokenizer.current();
		Lexeme oper = current;
		ExpressionNode expr = parseExpression();
		
		return new ExpressionNode(term, expr, oper);
	}
	
	/**
	 * Starts with parseFactor(), peeks at next token, if it is not DIV_OP or MULT_OP, return.
	 * Else, continue parsing terms.
	 */
	public TermNode parseTerm() throws IOException, TokenizerException {
		FactorNode factor = parseFactor();
		Lexeme next = tokenizer.peek();
		
		if(next.token() != Token.MULT_OP && next.token() != Token.DIV_OP)
			return new TermNode(factor);
		
		current = tokenizer.current();
		Lexeme oper = current;
		
		return new TermNode(factor, parseTerm(), oper);
	}
	
	/*
	 * The current token should be INT_LIT, IDENT or LEFT_PAREN.
	 * If it is not LEFT_PAREN it will return either an INT_LIT or IDENT.
	 * Else, parse the expression that is inside the parens.
	 */
	public FactorNode parseFactor() throws IOException, TokenizerException {
		current = tokenizer.current();
		
		if(current.token() != Token.INT_LIT && current.token() != Token.IDENT && current.token() != Token.LEFT_PAREN)
			throw new TokenizerException("Expected INT_LIT or IDENT but was " + current.token());
		
		if(current.token() != Token.LEFT_PAREN) {
			FactorNode ret = null;
			
			if(current.token() == Token.INT_LIT)
				ret = new FactorNode(new Lexeme(Double.parseDouble((String) current.value()), Token.INT_LIT));
			else
				ret = new FactorNode(current);
			
			return ret;
		}
		
		ExpressionNode expr = parseExpression();
		current = tokenizer.current();
		
		if(current.token() != Token.RIGHT_PAREN)
			throw new TokenizerException("Expected RIGHT_PAREN but was " + current.token());
		
		return new FactorNode(expr);
	}

	@Override
	public void close() throws IOException {
		tokenizer.close();
	}
}