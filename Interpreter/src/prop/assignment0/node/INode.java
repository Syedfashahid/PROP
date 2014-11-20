package prop.assignment0.node;

public interface INode {
	/**
	 * The argument array 'args' is only needed for the requirements for grade A and B.
	 * When not needed just call evaluate with null as the actual parameter.
	 */
	Object evaluate(Object[] args) throws Exception; 
	
	void buildString(StringBuilder builder, int tabs);
	
	public static void appendString(StringBuilder builder, int tabs, String str) {
		for(int i = 0; i < tabs; i++)
			builder.append("\t");
		
		builder.append(str + "\n");
	}
}