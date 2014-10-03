public class Token {
    String text;
    int level;
    int regel;
    int pos;
    String partner;
    
    public Token(String text, int level, int regel, int pos)
    {
    	this.text = text;
    	this.level = level;
    	this.regel = regel;
    	this.pos = pos;
    }
    
    public Token(String text, int level, int regel, int pos, String partner)
    {
    	this.text = text;
    	this.level = level;
    	this.regel = regel;
    	this.pos = pos;
    	this.partner = partner;
    }
    
    public String toString()
    {
    	String r;
    	
    	r = "TOKEN( ";
    	r += text;
    	r += " )";
    	r += "\n";
    	
    	return r;
    }
}
