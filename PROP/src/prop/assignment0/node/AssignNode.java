package prop.assignment0.node;

public class AssignNode implements INode {
	private IdNode idNode;
	private ExprNode exprNode;
	
	public AssignNode(IdNode idNode, ExprNode exprNode) {
		this.idNode = idNode;
		this.exprNode = exprNode;
	}

	public IdNode getIdNode() {
		return idNode;
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