package prop.assignment0.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import prop.assignment0.Token;
import prop.assignment0.Tokenizer;
import prop.assignment0.TokenizerException;

public class TokenizerTests {

	@Test
	public void test() throws IOException, TokenizerException {
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.open("testFile");
		
		assertEquals(Token.IDENT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.ASSIGN_OP, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.INT_LIT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.SEMICOLON, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.EOF, tokenizer.current().token());
	}

}