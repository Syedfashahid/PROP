package prop.assignment0.tokenizer;

import java.io.IOException;

import prop.assignment0.exception.TokenizerException;
import prop.assignment0.lexeme.Lexeme;
import prop.assignment0.lexeme.Token;
import prop.assignment0.scanner.Scanner;

public class Tokenizer implements ITokenizer {
	public static final char EOF = (char) -1;
	private Scanner scanner;
	private Lexeme current;
	private Lexeme next;
	
	public Tokenizer() {
		scanner = new Scanner();
	}
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner.open(fileName);
	}
	
	@Override
	public Lexeme current() throws IOException, TokenizerException {
		if(current == null)
			current = tokenize();
		else
			current = next;
		
		if(current.token() != Token.EOF)
			next = tokenize();
		
		return current;
	}
	
	private Lexeme tokenize() throws IOException, TokenizerException {
		moveNext();		
		
		while(Character.isWhitespace(scanner.current()))
			moveNext();
		
		char currentChar = scanner.current();
		
		switch(currentChar) {
		case '=':
			return new Lexeme(currentChar, Token.ASSIGN_OP);
		case '+':
			return new Lexeme(currentChar, Token.ADD_OP);
		case '-':
			return new Lexeme(currentChar, Token.SUB_OP);
		case '*':
			return new Lexeme(currentChar, Token.MULT_OP);
		case '/':
			return new Lexeme(currentChar, Token.DIV_OP);
		case '(':
			return new Lexeme(currentChar, Token.LEFT_PAREN);
		case ')':
			return new Lexeme(currentChar, Token.RIGHT_PAREN);
		case '{':
			return new Lexeme(currentChar, Token.LEFT_CURLY);
		case '}':
			return new Lexeme(currentChar, Token.RIGHT_CURLY);
		case ';':
			return new Lexeme(currentChar, Token.SEMICOLON);
		case EOF:
			return new Lexeme(currentChar, Token.EOF);
		default:
			StringBuilder sb = new StringBuilder();
			sb.append(currentChar);
			
			if(Character.isAlphabetic(currentChar)) {
				while(Character.isAlphabetic(scanner.peek())) {
					moveNext();
					sb.append(scanner.current());
				}
				
				return new Lexeme(sb.toString(), Token.IDENT);
			} else if(Character.isDigit(currentChar)) {
				while(Character.isDigit(scanner.peek())) {
					moveNext();
					sb.append(scanner.current());
				}
				
				return new Lexeme(sb.toString(), Token.INT_LIT);
			}
			
			throw new TokenizerException("Failed to create Lexeme from char: <" + currentChar +">");
		}
	}
	
	public Lexeme peek() {
		return next;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
		scanner.moveNext();
	}

	@Override
	public void close() throws IOException {
		scanner.close();
	}
}