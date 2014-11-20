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
		if(assign == null && stmts == null)
			return null;
		
		else if(assign != null && stmts != null) {
			Object stmtsEval = stmts.evaluate(null);
			Object assignEval = assign.evaluate(null);
			
			if(stmtsEval == null) {
				String str = "";
				
				Object[] tmp = (Object[]) assignEval;
				str += tmp[0];
				str += " " + tmp[1];
				str += " " + tmp[2];
				
				return str;
			}
		}
		
		throw new Exception("Failed to evaluate StatementsNode");
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