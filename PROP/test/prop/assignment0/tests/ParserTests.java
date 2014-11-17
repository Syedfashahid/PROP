package prop.assignment0.tests;

import java.io.IOException;

import org.junit.Test;

import prop.assignment0.exception.ParserException;
import prop.assignment0.exception.TokenizerException;
import prop.assignment0.node.INode;
import prop.assignment0.parser.Parser;

public class ParserTests {
	@Test
	public void test() throws IOException, TokenizerException, ParserException {
		Parser parser = new Parser();
		parser.open("testFile2");
		INode tree = parser.parse();
		System.out.println(tree);
	}
}