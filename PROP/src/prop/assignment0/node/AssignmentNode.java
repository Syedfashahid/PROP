package prop.assignment0.node;

import prop.assignment0.tokenizer.Lexeme;

public class AssignmentNode implements INode {
	public Lexeme id;
	public ExpressionNode expr;
	
	public AssignmentNode(Lexeme id, ExpressionNode expr) {
		this.id = id;
		this.expr = expr;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		
	}
	
	@Override
	public String toString() {
		return "AssignmentNode\nid: " + id + "\nexpr: " + expr;
	}
}