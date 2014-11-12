package prop.assignment0.node;

public class ExprNode implements INode {
	private TermNode term;
	private ExprNode expr;
	
	public ExprNode(TermNode term, ExprNode expr) {
		this.term = term;
		this.expr = expr;
	}
	
	public TermNode getTerm() {
		return term;
	}

	public ExprNode getExpr() {
		return expr;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

	}
}