package prop.assignment0.node;

import prop.assignment0.tokenizer.IntNode;

public class FactorNode implements INode {
	private IntNode intNode;
	private ExprNode exprNode;
	
	public FactorNode(IntNode intNode, ExprNode exprNode) {
		this.intNode = intNode;
		this.exprNode = exprNode;
	}

	public IntNode getIntNode() {
		return intNode;
	}

	public ExprNode getExprNode() {
		return exprNode;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

	}
}