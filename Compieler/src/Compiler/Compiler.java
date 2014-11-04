package Compiler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Main.Frame;
import Tokenizer.Token;
import Tokenizer.TokenList;
import CompilerNodes.*;

public class Compiler {
	
	TokenList t;
	Frame f;
	LinkedList<Node> nodeList;
	
	public Compiler(Frame f, TokenList tokens)
	{
		this.f = f;
		this.t = tokens;
		this.nodeList = new LinkedList<Node>();
	}
	
	public LinkedList<Node> start() //throws CompilerError
	{
		f.append("\nSTART COMPILING\n");
		
		TokenList current = t;
		List<TokenList> pieceList = splitList(current);
		
		for(Iterator<TokenList> i = pieceList.iterator(); i.hasNext(); ) {
			TokenList item = i.next();
		    
			handlePiece(item);
		}
		
		for(Iterator<Node> i = nodeList.iterator(); i.hasNext(); ) {
			Node item = i.next();
		    
			f.append("node: " + item.toString() + "\n" );
		}
		
		return nodeList;
	}

    private List<TokenList> splitList(TokenList tokenList)
    {
    	List<TokenList> splitList = new ArrayList<TokenList>();
        TokenList partstart = new TokenList(new Token(Token.TokenEnum.START, null,
				0, 0, 0));
        TokenList part = partstart;
        TokenList current = tokenList;
		
		while(current.next != null)
		{
			current = current.next;
			
			part.setNext(new TokenList(current.t));
			part = part.next;
			
			if(current.t.token == Token.TokenEnum.CBCLOSE)
			{
				splitList.add(partstart);
				partstart = new TokenList(new Token(Token.TokenEnum.START, null,
						0, 0, 0));
		        part = partstart;
		    } else if(current.t.token == Token.TokenEnum.SEMICOLON)
			{
		    	splitList.add(partstart);
				partstart = new TokenList(new Token(Token.TokenEnum.START, null,
						0, 0, 0));
		        part = partstart;
			}
		}

        return splitList;
    }
	
	
	
    private void handlePiece(TokenList piece)
    {
    	piece = piece.next;
    	//f.append(piece.t.token.toString());
    	
    	if(piece.t.token == Token.TokenEnum.IF)
    	{
    		_if(piece);
    	}

    	if (piece.t.token == Token.TokenEnum.STRING)
        {
            //piece.RemoveAt(piece.Count - 1);
            nodeList.addLast(new Assignment(piece));
        } 
    }
    

    
    
    private void _if(TokenList piece)
    {
    	
    	Condition condition = new Condition(_condition(piece));
        nodeList.addLast(condition);

        ConditionalJump conditionaljump = new ConditionalJump();
        nodeList.addLast(conditionaljump);

        DoNothing donothing_true = new DoNothing();
        nodeList.addLast(donothing_true);
        
        conditionaljump.jump_true = nodeList.getLast();

        List<TokenList> statements = _statement(piece);
        
        f.append(statements.toString());
        
        for(Iterator<TokenList> i = statements.iterator(); i.hasNext(); ) {
			TokenList item = i.next();
		    
			handlePiece(item);
		}
        

        DoNothing donothing_false = new DoNothing();
        nodeList.addLast(donothing_false);
        conditionaljump.jump_false = nodeList.getLast();
    }
    
    private TokenList _condition(TokenList condition)
    {
    	boolean con = false;
    	
    	TokenList partstart = new TokenList(new Token(Token.TokenEnum.START, null,
				0, 0, 0));
        TokenList part = partstart;
        TokenList current = condition;
		
        while(current.next != null)
		{
			current = current.next;
			
			
			if(current.t.token == Token.TokenEnum.BOPEN)
			{
                con = true;
            }
			else if (current.t.token == Token.TokenEnum.BCLOSE)
            {
				return partstart;
            }
			
			if (con && !(current.t.token == Token.TokenEnum.BOPEN))
            {
				part.setNext(new TokenList(current.t));
				part = part.next;
            }
		}
		
		return condition;
    }
    
    private List<TokenList> _statement(TokenList statement)
    {
    	boolean state = false;
    	TokenList partstart = new TokenList(new Token(Token.TokenEnum.START, null,
				0, 0, 0));
        TokenList part = partstart;
        TokenList current = statement;
		
		while(current.next != null)
		{
			current = current.next;
			
			if (current.t.token == Token.TokenEnum.CBOPEN)
            {
                state = true;
            }
			else if (current.t.token == Token.TokenEnum.CBCLOSE)
            {
                break;
            }
			
			if (state && !(current.t.token == Token.TokenEnum.CBOPEN))
            {
				part.setNext(new TokenList(current.t));
				part = part.next;
            }
		}

        return splitList(partstart);
    }
}
