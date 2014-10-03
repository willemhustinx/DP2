import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {
    
    Frame f;
    
    public Tokenizer(Frame f)
    {
        this.f = f;  
    }
    
    public void start() throws TokenizerError
    {
        f.append("start");
        
        String s = f.get();
        StringTokenizer st = new StringTokenizer(s);
        
        char c;
		int line = 1;
		int level = 1;
		int positie = 1;
		int startPos = 1;
		int i = 0;
		String token = "";
		
		List<Token> tokenList = new ArrayList<Token>();

		while (i < s.length())
		{
			c = s.charAt(i);
			
			if(c == '\n')
			{
				line++;
				positie = 1;	
			}
			
			if(c == '\t')
			{
				positie += 4;	
			}
			
			if(c == '(')
			{
				tokenList.add(new Token("(", line, startPos, level, ")"));
				level++;
			}
			if(c == '{')
			{
				tokenList.add(new Token("{", line, startPos, level, "}"));
				level++;
			}
			
			if(c == ')')
			{
				tokenList.add(new Token(")", line, startPos, level, "("));	
				level--;
			}
			if(c == '}')
			{
				tokenList.add(new Token("}", line, startPos, level, "{"));	
				level--;
			}
			if(c == ';')
			{
				tokenList.add(new Token(";", line, startPos, level));
			}
			
			// For integers
			if(Character.isDigit(c))
			{
				token += c;
				startPos = positie;
				
				i++;
				while (i < s.length())
				{
					c = s.charAt(i);
					if(Character.isDigit(c))
					{
						token += c;
					} else {
						if(c == ' ' || c == ';' || c == '(' || c == ')'){
							
							tokenList.add(new Token(token, line, startPos, level));
							token = "";
							i--;
							break;

						} else {
							throw new TokenizerError("fout met tokenizer op r" + line + "@" + positie + ", verwacht een digit" );
						}
					}
					
					positie++;
					i++;
				}
			}
			
			// For Strings
			if(Character.isLetter(c))
			{
				token += c;
				startPos = positie;
				
				i++;
				while (i < s.length())
				{
					c = s.charAt(i);
					if(Character.isLetter(c))
					{
						token += c;
					} else {
						if(c == ' ' || c == ';' || c == '(' || c == ')'){
							
							if(token.toLowerCase() == "if")
							{
								tokenList.add(new Token(token, line, startPos, level, "else"));
							}
							else
							{
								tokenList.add(new Token(token, line, startPos, level));
							}
							
							token = "";
							i--;
							break;

						} else {
							throw new TokenizerError("fout met tokenizer op r" + line + "@" + positie + ", verwacht een letter" );
						}
					}
					
					positie++;
					i++;
				}
			}
			
			if(c == '=')
			{
				token += c;
				startPos = positie;
				
				c = s.charAt(i+1);
				if(c == '=')
				{
					token += c;
					positie++;
					i++;
				}
				
				tokenList.add(new Token(token, line, startPos, level));
				token = "";
			}
			
			positie++;
			i++;
			
        }
        
		
		f.out(tokenList.toString());
    }
}







