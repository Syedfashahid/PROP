package prop.assignment0.node;

import java.util.HashMap;

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
			Object assignEval = assign.evaluate(args);
			
			Object[] tmp2 = (Object[]) assignEval;
			System.out.println(tmp2[0] + " " + tmp2[1] + " " + tmp2[2]);
			HashMap<Character, Double> map = new HashMap<>();
			map.put((Character) tmp2[0], (Double) tmp2[2]);
			Object[] newArgs = new Object[1];
			newArgs[0] = map;
			Object stmtsEval = stmts.evaluate(newArgs);
			
			if(stmtsEval == null)
				return assignEval;
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