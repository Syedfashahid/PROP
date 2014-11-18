package prop.assignment0.tokenizer;

import java.io.IOException;

import prop.assignment0.exception.TokenizerException;
import prop.assignment0.scanner.Scanner;

public class Tokenizer implements ITokenizer {
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
		if(next == null) {
			current = getNext();
			next = getNext();
		} else {
			current = next;
			next = getNext();
		}
		
		return current;
	}
	
	public Lexeme peek() {
		return next;
	}
	
	public Lexeme getNext() throws IOException, TokenizerException {
		moveNext();
		char currentChar = scanner.current();

		while(Character.isWhitespace(currentChar)) {
			moveNext();
			currentChar = scanner.current();
		}

		switch(currentChar) {
		case (char) -1:
			return new Lexeme(currentChar, Token.EOF);
		case '=':
			return new Lexeme(currentChar, Token.ASSIGN_OP);
		case ';':
			return new Lexeme(currentChar, Token.SEMICOLON);
		case '(':
			return new Lexeme(currentChar, Token.LEFT_PAREN);
		case '+':
			return new Lexeme(currentChar, Token.ADD_OP);
		case '-':
			return new Lexeme(currentChar, Token.SUB_OP);
		case ')':
			return new Lexeme(currentChar, Token.RIGHT_PAREN);
		case '*':
			return new Lexeme(currentChar, Token.MULT_OP);
		case '/':
			return new Lexeme(currentChar, Token.DIV_OP);
		default:
			String str = Character.toString(currentChar);

			if(Character.isAlphabetic(currentChar)) {
				while(Character.isAlphabetic(scanner.peek())) {
					moveNext();
					str += scanner.current();
				}

				return new Lexeme(str, Token.IDENT);
			}

			else if(Character.isDigit(currentChar)) {
				while(Character.isDigit(scanner.peek())) {
					moveNext();
					str += scanner.current();
				}

				return new Lexeme(Double.parseDouble(str), Token.INT_LIT);
			}

			throw new TokenizerException("Failed to categorize char: '" + currentChar + "'");
		}
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