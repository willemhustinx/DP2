package Tokenizer;
public class Token {
    String text;
    int level;
    int regel;
    int pos;
    public TokenEnum token;
    
    public Token(TokenEnum token, String text, int regel, int pos, int level)
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
    	r += token.toString();
    	r += ", " + text;
    	r += ", " + level;
    	r += ", " + regel + "@" + pos;
    	r += " )";
    	
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
