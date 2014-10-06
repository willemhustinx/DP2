package Compiler;
import Main.Frame;
import Tokenizer.Token;
import Tokenizer.TokenList;

public class CompilerIf extends Compiler {

	public CompilerIf(Frame f, TokenList tokens) {
		super(f, tokens);
		// TODO Auto-generated constructor stub
	}
	
	public void start() throws CompilerError
	{
		TokenList current = t;
		
		if(current.next.t.token == Token.TokenEnum.BOPEN)
		{
			current = current.next;
			
			TokenList conditionStart = new TokenList(new Token(Token.TokenEnum.START, null, 0, 0, 0));
			TokenList condition = conditionStart;
			
			f.append("\n\n" + current.next.t.toString());
			
			while(current.next.t.token != Token.TokenEnum.BCLOSE)
			{
				f.append("\nwhile:" + current.next.t.toString());
				
				if(current.next != null)
				{	
					current = current.next;
					condition.setNext(new TokenList(current.t));
					condition = condition.next;
				}
				else
				{
					throw new CompilerError("Missing condition from if statement");
				}			
			}
			
			//Copile Condition;
			
			f.append("\n\ncon:" + conditionStart.toString());
			current = current.next;
			
			if(current.next.t.token == Token.TokenEnum.CBOPEN)
			{
				current = current.next;
				
				TokenList bodyStart = new TokenList(new Token(Token.TokenEnum.START, null, 0, 0, 0));
				TokenList body = bodyStart;
				
				f.append("\n\n" + current.next.t.toString());
				
				while(current.next.t.token != Token.TokenEnum.CBCLOSE)
				{
					f.append("\nwhile:" + current.next.t.toString());
					
					if(current.next != null)
					{	
						current = current.next;
						body.setNext(new TokenList(current.t));
						body = body.next;
					}
					else
					{
						throw new CompilerError("Missing body from if statement");
					}			
				}
								
				//Compile body
				f.append("\n\nbody:" + bodyStart.toString());
				

			}
			else
			{
				throw new CompilerError("Missing body from if statement");
			}
			
		}
		else
		{
			throw new CompilerError("Missing condition from if statement");
		}
		
	}

}
