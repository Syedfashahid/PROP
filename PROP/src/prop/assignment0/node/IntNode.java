package prop.assignment0.node;

public class IntNode implements INode {
	private int data;
	
	public IntNode(int data) {
		this.data = data;
	}
	
	public int getData() {
		return data;
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		
	}
}