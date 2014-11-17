package prop.assignment0.tokenizer;

import java.io.IOException;

import prop.assignment0.exception.TokenizerException;
import prop.assignment0.scanner.Scanner;

public class Tokenizer implements ITokenizer {
	private Scanner scanner;
	
	public Tokenizer() {
		scanner = new Scanner();
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner.open(fileName);
	}

	@Override
	public IntNode current() throws IOException, TokenizerException {
		moveNext();
		char currentChar = scanner.current();

		while(Character.isWhitespace(currentChar)) {
			moveNext();
			currentChar = scanner.current();
		}

		switch(currentChar) {
		case (char) -1:
			return new IntNode(currentChar, Token.EOF);
		case '=':
			return new IntNode(currentChar, Token.ASSIGN_OP);
		case ';':
			return new IntNode(currentChar, Token.SEMICOLON);
		case '(':
			return new IntNode(currentChar, Token.LEFT_PAREN);
		case '+':
			return new IntNode(currentChar, Token.ADD_OP);
		case '-':
			return new IntNode(currentChar, Token.SUB_OP);
		case ')':
			return new IntNode(currentChar, Token.RIGHT_PAREN);
		case '*':
			return new IntNode(currentChar, Token.MULT_OP);
		case '/':
			return new IntNode(currentChar, Token.DIV_OP);
		default:
			String str = Character.toString(currentChar);

			if(Character.isAlphabetic(currentChar)) {
				while(Character.isAlphabetic(scanner.peek())) {
					moveNext();
					str += scanner.current();
				}

				return new IntNode(str, Token.IDENT);
			}

			else if(Character.isDigit(currentChar)) {
				while(Character.isDigit(scanner.peek())) {
					moveNext();
					str += scanner.current();
				}

				return new IntNode(str, Token.INT_LIT);
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