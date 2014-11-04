package CompilerNodes;

public class ConditionalJump extends Node {

	public Node jump_true;
	public Node jump_false;
	
	public ConditionalJump() {
		// TODO Auto-generated constructor stub
	}
	
	public Node jump(boolean result)
	{
		if(result)
        {
            return jump_true;
        }
        else
        {
            return jump_false;
        }
	}
}
