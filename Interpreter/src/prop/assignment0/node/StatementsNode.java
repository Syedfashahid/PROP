package prop.assignment0.node;

import java.util.HashMap;
import java.util.Map;

public class StatementsNode implements INode {
	private AssignmentNode assign;
	private StatementsNode stmts;
	
	public StatementsNode() {
	}
	
	public StatementsNode(AssignmentNode assign, StatementsNode stmts) {
		this.assign = assign;
		this.stmts = stmts;
	}
	
	/**
	 * Returns null if the StatementsNode is empty.
	 * Else, evaluates assign and saves the results (variable and it's value) in the HashMap.
	 * If there are no more statements to evaluate, return a string built upon the contents of the final map.
	 */
	@Override
	public Object evaluate(Object[] args, HashMap<String, Double> map) throws Exception {
		if(assign == null)
			return null;
		
		Object assignEval = assign.evaluate(args, map);
		Object[] tmp = (Object[]) assignEval;
		
		if(tmp[2] instanceof String)
			tmp[2] = map.get(tmp[2]);
		
		map.put((String) tmp[0], (Double) tmp[2]);
		Object stmtsEval = stmts.evaluate(null, map);
		
		if(stmtsEval == null) {			
			String str = "";
			
			for(Map.Entry<String, Double> me : map.entrySet()) {
				double value = Math.round(me.getValue() * 100.0) / 100.0;
				str += me.getKey() + " = " + value + "\n";
			}
			
			return str;
		}
		
		return stmtsEval;
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