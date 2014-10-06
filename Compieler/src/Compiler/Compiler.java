package Compiler;
import Main.Frame;
import Tokenizer.Token;
import Tokenizer.TokenList;

public class Compiler {
	
	TokenList t;
	Frame f;
	
	public Compiler(Frame f, TokenList tokens)
	{
		this.f = f;
		this.t = tokens;
	}
	
	public void start() throws CompilerError
	{
		f.append("\nSTART COMPILING\n");
		
		TokenList current = t;
		
		while(current.next != null)
		{
			current = current.next;
			
			if(current.t.token == Token.TokenEnum.IF)
			{
				CompilerIf c = new CompilerIf(f, current);
				c.start();
			}
			
		}
	}
}
