public class Token {
    String text;
    int level;
    int regel;
    int pos;
    String partner;
    TokenEnum partnerEnum;
    TokenEnum token;
    
    public Token(TokenEnum token, String text, int level, int regel, int pos, TokenEnum partnerEnum, String partner)
    {
    	this.token = token;
    	this.text = text;
    	this.level = level;
    	this.regel = regel;
    	this.pos = pos;
    	this.partnerEnum = partnerEnum;
    	this.partner = partner;
    }
    
    public Token(TokenEnum token, String text, int level, int regel, int pos)
    {
    	this.token = token;
    	this.text = text;
    	this.level = level;
    	this.regel = regel;
    	this.pos = pos;
    }
    
    public String toString()
    {
    	String r;
    	
    	r = "TOKEN( ";
    	r += token.getToken().toUpperCase();
    	r += ", " + text;
    	r += " )";
    	r += "\n";
    	
    	return r;
    }
    
	public static enum TokenEnum {
		
		FUNCTION("function"),
		IF("if"),
		ELSE("else"),
		WHILE("while"),
		STRING("string"),
		DIGIT("digit"),
		OPERATOR("operator"),
		BOPEN("("),BCLOSE(")"),
		CBOPEN("{"),CBCLOSE("}"),
		SEMICOLON(";"),
		START("starttoken");
		

		private String token;

		private TokenEnum(String token) {

			this.token = token;
		}

		public String getToken() {

			return this.token;
		}
	}
}
