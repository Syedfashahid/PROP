package prop.assignment0.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import prop.assignment0.exception.TokenizerException;
import prop.assignment0.tokenizer.Token;
import prop.assignment0.tokenizer.Tokenizer;

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
		assertEquals(Token.MULT_OP, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.INT_LIT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.ADD_OP, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.LEFT_PAREN, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.INT_LIT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.SUB_OP, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.INT_LIT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.RIGHT_PAREN, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.DIV_OP, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.INT_LIT, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.SEMICOLON, tokenizer.current().token());
		
		tokenizer.moveNext();
		assertEquals(Token.EOF, tokenizer.current().token());
	}

}