package Compiler;

public class Jump extends CompilerList {
	public CompilerList jumpto;
	
	public void jump(CompilerList jump)
	{
		this.jumpto = jump;
	} 

}
