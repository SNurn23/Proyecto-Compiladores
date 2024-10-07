package lexer;

public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType Type, String match) {
        this.type = Type;
        this.value = match;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value='" + value + '\'' + '}';
    }
}
