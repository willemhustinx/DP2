package Compiler;

public class CompilerList {
	public CompilerList next;
	public CompilerList prev;
	
	public CompilerList()
	{
	}
	
	public void append(CompilerList next)
	{
		this.next = next;
		next.prev = this;
	}
	
	public void prepend(CompilerList prev)
	{
		prev.prev = this.prev;
		this.prev.next = prev;
		
		prev.next = this;
		this.prev = prev;
	}
	
	public String toString()
	{
		String r = "CompilerList, ";
		
		r += this.next.toString();
		
		return r;
	}
}