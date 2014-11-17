package prop.assignment0.node;

public class ExpressionNode implements INode {
	public TermNode term;
	public ExpressionNode expr;
	
	public ExpressionNode() {
		
	}
	
	public ExpressionNode(TermNode term, ExpressionNode expr) {
		this.term = term;
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
		String str = "ExpressionNode\n\t" + term;
		
		if(expr != null)
			str += "\n\t" + expr;
		
		return str;
	}
}