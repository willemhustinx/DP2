package CompilerNodes;

public class Jump extends Node {

	public LinkedListNode jump_location;
	
	public Jump() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedListNode jump()
	{
		return jump_location;
	}

}