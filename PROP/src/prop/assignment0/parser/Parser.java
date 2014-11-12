package prop.assignment0.parser;

import java.io.IOException;

import prop.assignment0.exception.ParserException;
import prop.assignment0.exception.TokenizerException;
import prop.assignment0.node.AssignNode;
import prop.assignment0.node.INode;
import prop.assignment0.tokenizer.Tokenizer;

public class Parser implements IParser {
	private Tokenizer tokenizer;
	
	public Parser() {
		tokenizer = new Tokenizer();
	}
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {	
		tokenizer.open(fileName);
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		return null;
	}
	
	public AssignNode parseAssign() {
		return null;
	}

	@Override
	public void close() throws IOException {
		tokenizer.close();
	}
}