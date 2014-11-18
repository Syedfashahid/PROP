package prop.assignment0.parser;

import java.io.IOException;

import prop.assignment0.exception.ParserException;
import prop.assignment0.exception.TokenizerException;
import prop.assignment0.node.AssignmentNode;
import prop.assignment0.node.ExpressionNode;
import prop.assignment0.node.FactorNode;
import prop.assignment0.node.INode;
import prop.assignment0.node.TermNode;
import prop.assignment0.tokenizer.Lexeme;
import prop.assignment0.tokenizer.Token;
import prop.assignment0.tokenizer.Tokenizer;

public class Parser implements IParser {
	private Tokenizer tokenizer;
	private Lexeme current;
	private Lexeme next;
	
	public Parser() {
		tokenizer = new Tokenizer();
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer.open(fileName);
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		current = tokenizer.current();
		
		if(current.token() == Token.IDENT)
			return parseAssignment();
		
		throw new ParserException("Syntax error.");
	}
	
	public AssignmentNode parseAssignment() throws IOException, TokenizerException, ParserException {
		Lexeme id = current;
		current = tokenizer.current();
		
		if(current.token() != Token.ASSIGN_OP)
			throw new ParserException("Syntax error.");
		
		ExpressionNode expression = parseExpression();
		current = tokenizer.current();
		
		if(current.token() != Token.SEMICOLON)
			throw new ParserException("Expected ASSIGN_OP or SEMICOLON but was " + current.token());
		
		return new AssignmentNode(id, expression);
	}
	
	public ExpressionNode parseExpression() throws IOException, TokenizerException, ParserException {
		TermNode term = parseTerm();
		ExpressionNode expression = null;
		Lexeme operator = null;
		//current = tokenizer.current();
		next = tokenizer.peek();
		
		if(next.token() == Token.ADD_OP || next.token() == Token.SUB_OP) {
			operator = tokenizer.current();
			expression = parseExpression();
		}
			
		return new ExpressionNode(term, expression, operator);
	}
	
	public TermNode parseTerm() throws IOException, TokenizerException, ParserException {
		FactorNode factor = parseFactor();
		TermNode term = null;
		Lexeme op = null;
		//current = tokenizer.current();
		next = tokenizer.peek();
		
		if(next.token() == Token.RIGHT_PAREN)
			tokenizer.current();
		
		if(next.token() == Token.DIV_OP || next.token() == Token.MULT_OP) {
			op = tokenizer.current();
			term = parseTerm();
		}
		
		return new TermNode(factor, term, op);
	}
	
	public FactorNode parseFactor() throws IOException, TokenizerException, ParserException {
		//current = tokenizer.current();
		next = tokenizer.peek();
		
		if(next.token() == Token.INT_LIT)
			return new FactorNode(tokenizer.current(), null);
		
		if(next.token() == Token.LEFT_PAREN) {
			tokenizer.current();
			return new FactorNode(null, parseExpression());
		}
		
		throw new ParserException("Expected INT_LIT or LEFT_PAREN but was " + next.token());
	}

	@Override
	public void close() throws IOException {
		tokenizer.close();
	}
}