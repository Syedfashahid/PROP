package prop.assignment0.node;

public class TermNode implements INode {
	private FactorNode factorNode;
	private TermNode termNode;
	
	public TermNode(FactorNode factorNode, TermNode termNode) {
		this.factorNode = factorNode;
		this.termNode = termNode;
	}
	
	public FactorNode getFactor() {
		return factorNode;
	}

	public TermNode getTerm() {
		return termNode;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {

	}
}