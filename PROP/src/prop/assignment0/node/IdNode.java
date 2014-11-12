package prop.assignment0.node;

public class IdNode implements INode {
	private String data;
	
	public IdNode(String data) {
		this.data = data;
	}
	
	public String getData() {
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