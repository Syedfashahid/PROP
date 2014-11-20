package prop.assignment0.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import prop.assignment0.exception.TokenizerException;
import prop.assignment0.lexeme.Token;
import prop.assignment0.tokenizer.Tokenizer;

public class TokenizerTest {
	public void testSequence(Tokenizer tokenizer, Token... tokens) throws IOException, TokenizerException {
		for(Token t : tokens)
			assertEquals(tokenizer.current().token(), t);
	}

	@Test
	public void testProgram0() throws IOException, TokenizerException {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.open("txt/program0.txt");
		testSequence(tokenizer, Token.IDENT, Token.ASSIGN_OP, Token.INT_LIT, Token.SEMICOLON, Token.EOF);
	}

	@Test
	public void testProgram1() throws IOException, TokenizerException {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.open("txt/program1.txt");
		testSequence(tokenizer, Token.IDENT, Token.ASSIGN_OP, Token.INT_LIT, Token.MULT_OP, Token.INT_LIT, Token.ADD_OP, Token.LEFT_PAREN,
				Token.INT_LIT, Token.SUB_OP, Token.INT_LIT, Token.RIGHT_PAREN, Token.DIV_OP, Token.INT_LIT, Token.SEMICOLON, Token.EOF);
	}

	@Test
	public void testProgram2() throws IOException, TokenizerException {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.open("txt/program2.txt");
		testSequence(tokenizer, Token.LEFT_CURLY, Token.IDENT, Token.ASSIGN_OP, Token.INT_LIT, Token.MULT_OP, Token.INT_LIT, Token.ADD_OP,
				Token.LEFT_PAREN, Token.INT_LIT, Token.SUB_OP, Token.INT_LIT, Token.RIGHT_PAREN, Token.DIV_OP, Token.INT_LIT, Token.SEMICOLON,
				Token.IDENT, Token.ASSIGN_OP, Token.INT_LIT, Token.SUB_OP, Token.INT_LIT, Token.SUB_OP, Token.IDENT, Token.ADD_OP, Token.INT_LIT,
				Token.DIV_OP, Token.INT_LIT, Token.DIV_OP, Token.INT_LIT, Token.SEMICOLON, Token.IDENT, Token.ASSIGN_OP, Token.IDENT, Token.ADD_OP,
				Token.IDENT, Token.SEMICOLON, Token.RIGHT_CURLY, Token.EOF);
	}
}