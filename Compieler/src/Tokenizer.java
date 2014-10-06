import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {

	Frame f;

	public Tokenizer(Frame f) {
		this.f = f;
	}

	public TokenList start() throws TokenizerError {
		f.append("\nSTART TOKENIZER\n");
		
		TokenList start = new TokenList(new Token(Token.TokenEnum.START, null, 0, 0, 0));
		
		TokenList current = start;

		String s = f.get();
		StringTokenizer st = new StringTokenizer(s);

		char c;
		int line = 1;
		int level = 1;
		int positie = 1;
		int startPos = 1;
		int i = 0;
		String token = "";

		while (i < s.length()) {
			c = s.charAt(i);

			if (c == '\n') {
				line++;
				positie = 1;
			}

			else if (c == '\t') {
				positie += 4;
			}

			else if (c == '(') {
				current.setNext(new TokenList(new Token(Token.TokenEnum.BOPEN, "(", line, startPos, level, Token.TokenEnum.BCLOSE, ")")));
				current = current.next;
				level++;
			}
			else if (c == '{') {
				current.setNext(new TokenList(new Token(Token.TokenEnum.CBOPEN, "{", line, startPos, level, Token.TokenEnum.CBCLOSE, "}")));
				current = current.next;
				level++;
			}
			else if (c == ')') {
				current.setNext(new TokenList(new Token(Token.TokenEnum.BCLOSE, ")", line, startPos, level,Token.TokenEnum.BOPEN,  "(")));
				current = current.next;
				level--;
			}
			else if (c == '}') {
				current.setNext(new TokenList(new Token(Token.TokenEnum.CBCLOSE, "}", line, startPos, level,Token.TokenEnum.CBOPEN,  "{")));
				current = current.next;
				level--;
			}
			else if (c == ';') {
				current.setNext(new TokenList(new Token(Token.TokenEnum.SEMICOLON, ";", line, startPos, level)));
				current = current.next;
			}

			// For integers
			else if (Character.isDigit(c)) {
				token += c;
				startPos = positie;

				i++;
				while (i < s.length()) {
					c = s.charAt(i);
					if (Character.isDigit(c)) {
						token += c;
					} else {
						if (c == ' ' || c == ';' || c == '(' || c == ')') {

							current.setNext(new TokenList(new Token(Token.TokenEnum.DIGIT, token, line, startPos,
									level)));
							current = current.next;
							token = "";
							i--;
							break;

						} else {
							throw new TokenizerError("fout met tokenizer op r"
									+ line + "@" + positie
									+ ", verwacht een digit");
						}
					}

					positie++;
					i++;
				}
			}

			// For Strings
			else if (Character.isLetter(c)) {
				token += c;
				startPos = positie;

				i++;
				while (i < s.length()) {
					c = s.charAt(i);
					if (Character.isLetter(c)) {
						token += c;
					} else {
						if (c == ' ' || c == ';' || c == '(' || c == ')') {
							
							if (token.toLowerCase().equals("if")) {
								current.setNext(new TokenList(new Token(Token.TokenEnum.IF, token, line, startPos,
										level, Token.TokenEnum.ELSE, "else")));
								current = current.next;
							} else if (token.toLowerCase().equals("else")) {
								current.setNext(new TokenList(new Token(Token.TokenEnum.ELSE, token, line, startPos,
										level)));
								current = current.next;
							} else if (token.toLowerCase().equals("function")) {
								current.setNext(new TokenList(new Token(Token.TokenEnum.FUNCTION, token, line, startPos,
										level)));
								current = current.next;
							} else if (token.toLowerCase().equals("while")) {
								current.setNext(new TokenList(new Token(Token.TokenEnum.WHILE, token, line, startPos, level)));
								current = current.next;
							} else {
								current.setNext(new TokenList(new Token(Token.TokenEnum.STRING, token, line, startPos,
										level)));
								current = current.next;
							}

							token = "";
							i--;
							break;

						} else {
							throw new TokenizerError("fout met tokenizer op r"
									+ line + "@" + positie
									+ ", verwacht een letter");
						}
					}

					positie++;
					i++;
				}
			}

			else if (c == '=') {
				token += c;
				startPos = positie;

				c = s.charAt(i + 1);
				if (c == '=') {
					token += c;
					positie++;
					i++;
				}

				current.setNext(new TokenList(new Token(Token.TokenEnum.OPERATOR, token, line, startPos, level)));
				current = current.next;
				token = "";
			}
			
			else if(c != ' '){
				throw new TokenizerError("fout met tokenizer op r"
						+ line + "@" + positie
						+ ", onbekend caracter");
			}

			positie++;
			i++;

		}
		
		f.append(start.toString());
		
		return start;
	}
}
