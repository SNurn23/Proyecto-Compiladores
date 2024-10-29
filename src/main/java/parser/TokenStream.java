package parser;

import lexer.Token;
import java.util.List;

public class TokenStream {

    private final List<Token> tokens;
    private int currentPosition;

    public TokenStream(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    // Devuelve el token actual sin avanzar el cursor
    public Token peekToken() {
        if (currentPosition < tokens.size()) {
            return tokens.get(currentPosition);
        }
        return null; // Retorna null si ya no hay más tokens
    }

    // Devuelve el token actual y avanza el cursor
    public Token nextToken() {
        if (currentPosition < tokens.size()) {
            return tokens.get(currentPosition++);
        }
        return null; // Retorna null si ya no hay más tokens
    }

    // Retrocede el cursor en 1 posición
    public void previousToken() {
        if (currentPosition > 0) {
            currentPosition--;
        }
    }

    // Revisa si hemos llegado al final del archivo
    public boolean hasNext() {
        return currentPosition < tokens.size();
    }
}
