public class Compiler {
	
	TokenList tokens;
	Frame f;
	
	public Compiler(Frame f, TokenList tokens)
	{
		this.f = f;
		this.tokens = tokens;
	}
	
	public void start()
	{
		f.append("\nSTART COMPILING\n");
	}
}
