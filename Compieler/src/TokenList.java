
public class TokenList {
	Token t;
	TokenList next, prev;
	
	public TokenList(Token t)
	{
		this.t = t;
	}
	
	public void setNext(TokenList next)
	{
		this.next = next;
		next.setPrev(this);
	}
	
	public void setPrev(TokenList prev)
	{
		this.prev = prev;
	}
	
	public String toString()
	{
		String r = "";
		
		r += this.t.toString();
		r += "\n";
		
		if(this.next != null)
		{
			r += this.next.toString();
		}
		
		return r;
	}
}


