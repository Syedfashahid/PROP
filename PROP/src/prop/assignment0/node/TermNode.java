package prop.assignment0.node;

public class TermNode implements INode {
	public FactorNode factor;
	public TermNode term;
	
	public TermNode() {
		
	}
	
	public TermNode(FactorNode factor, TermNode term) {
		this.factor = factor;
		this.term = term;
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
		String str = "TermNode\n\t" + factor;
		
		if(term != null)
			str += "\n\t" + term;
		
		return str;
	}
}