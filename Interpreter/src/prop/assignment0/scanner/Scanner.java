package prop.assignment0.scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner implements IScanner {
	public static final char NULL = (char)0;
	public static final char EOF = (char)-1; // End of file.

	private FileInputStream stream = null;
	private InputStreamReader reader = null;
	private char current = NULL;
	private char next = NULL;

	public Scanner() {
	}

	public void open(String fileName) throws FileNotFoundException {
		stream = new FileInputStream(fileName);
		reader = new InputStreamReader(stream);
	}

	public char current() {
		return current;
	}

	public char peek() {
		return next;
	}

	/**
	 * Change: moveNext() now sets both current and next, making it possible to peek at the next character.
	 * Added for making the logic simpler in the tokenizer and for convenience.
	 * If current is not set current will be the first character and next will be the second character.
	 * After that current will be set to next and next will be the character after that.
	 */
	public void moveNext() throws IOException {
		if (reader == null)
			throw new IOException("No open file.");
		if(current == NULL)
			current = (char) reader.read();
		else
			current = next;
		
		if(current != EOF)
			next = (char) reader.read();
	}

	public void close() throws IOException {
		if (reader != null)
			reader.close();
		if (stream != null)
			stream.close();
	}
}
