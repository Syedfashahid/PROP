package prop.assignment0.node;

public class StatementsNode implements INode {
	private AssignmentNode assign;
	private StatementsNode stmts;
	
	public StatementsNode() {
		
	}
	
	public StatementsNode(AssignmentNode assign, StatementsNode stmts) {
		this.assign = assign;
		this.stmts = stmts;
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		INode.appendString(builder, tabs, "StatementsNode");
		
		if(assign != null) {
			assign.buildString(builder, tabs + 1);
			stmts.buildString(builder, tabs + 1);
		}
	}
}