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
	private Lexeme curLex;
	
	public Parser() {
		tokenizer = new Tokenizer();
	}
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {	
		tokenizer.open(fileName);
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		curLex = tokenizer.current();
		Token token = curLex.token();
		INode root = null;
		
		if(token == Token.IDENT)
			root = parseAssign();
		else
			throw new ParserException("Syntax error.");
		
		return root;
	}
	
	public AssignmentNode parseAssign() throws IOException, TokenizerException, ParserException {
		Lexeme id = curLex;
		
		if(tokenizer.current().token() != Token.ASSIGN_OP)
			throw new ParserException("Syntax error.");
		
		ExpressionNode expr = parseExpr();
		
		return new AssignmentNode(id, expr);
	}
	
	public ExpressionNode parseExpr() throws ParserException, IOException, TokenizerException {
		TermNode term = parseTerm();
		ExpressionNode expr = new ExpressionNode();
		expr.term = term;
		
		curLex = tokenizer.current();
		Token curTkn = curLex.token();
		
		if(curTkn == Token.SEMICOLON || curTkn == Token.EOF)
			return expr;
		
		//System.out.println(curTkn);
		
		if(curTkn != Token.ADD_OP && curTkn != Token.SUB_OP)
			throw new ParserException("Syntax error.");
		
		expr.expr = parseExpr();
		
		return expr;
	}
	
	public TermNode parseTerm() throws IOException, TokenizerException, ParserException {
		FactorNode fact = parseFact();
		TermNode term = new TermNode();
		term.factor = fact;
		
		return term;
	}
	
	public FactorNode parseFact() throws IOException, TokenizerException, ParserException {
		FactorNode fact = new FactorNode();
		curLex = tokenizer.current();
		
		if(curLex.token() != Token.INT_LIT)
			throw new ParserException("Syntax error.");
		
		Lexeme integer = curLex;
		curLex = tokenizer.current();
		
		
		if(curLex.token() == Token.SEMICOLON) {
			fact.integer = integer;
			return fact;
		}
			
		ExpressionNode expr = parseExpr();
		fact.expr = expr;
		
		return fact;
	}

	@Override
	public void close() throws IOException {
		tokenizer.close();
	}
}