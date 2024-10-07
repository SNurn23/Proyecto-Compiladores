package lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

///  Implementa el analizador léxico, que convierte el código fuente en una lista de tokens.

public class Lexer {
    private String input;
    private List<Token> tokens;

    // Expresiones regulares para los diferentes tipos de tokens
    private static final String KEYWORD_PATTERN = "\\b(if|else|while|for|int|char|return|void)\\b";
    private static final String IDENTIFIER_PATTERN = "\\b[a-zA-Z_][a-zA-Z_0-9]*\\b";
    private static final String INT_LIT_PATTERN = "\\b[0-9]+\\b";
    private static final String CHAR_LIT_PATTERN = "'(\\\\.|[^\\\\'])'";
    private static final String STRING_LIT_PATTERN = "\"(\\\\.|[^\\\\\"])*\""; // Soporte para cadenas
    private static final String OPERATOR_PATTERN = "[+\\-*/=!><&|]{1,2}";
    private static final String SYMBOL_PATTERN = "[(){};,]";
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final String COMMENT_PATTERN = "(//.*?$|/\\*.*?\\*/)";

    // Definir los patrones que vamos a buscar en el input, incluyendo cadenas y comentarios
    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
            String.join("|", COMMENT_PATTERN, KEYWORD_PATTERN, IDENTIFIER_PATTERN,
                    INT_LIT_PATTERN, CHAR_LIT_PATTERN, STRING_LIT_PATTERN,
                    OPERATOR_PATTERN, SYMBOL_PATTERN, WHITESPACE_PATTERN),
            Pattern.DOTALL | Pattern.MULTILINE
    );

    public Lexer(String input) {
        this.tokens = new ArrayList<>();
        this.input = input;
    }

    public List<Token> tokenize() throws Exception {
        Matcher matcher = TOKEN_PATTERNS.matcher(input);
        int lastMatchEnd = 0; //Rastrear errores léxicos

        while (matcher.find()) {
            String match = matcher.group();

            if (matcher.start() > lastMatchEnd) {
                throw new Exception("Error léxico: Caracter no reconocido en la posición " + lastMatchEnd);
            }

            lastMatchEnd = matcher.end();

            //Ignorar los comentarios
            if (match.matches(COMMENT_PATTERN)) {
                continue;
            }

            if (match.matches(WHITESPACE_PATTERN)) {
                continue;
            }

            if (match.matches(KEYWORD_PATTERN)) {
                tokens.add(new Token(TokenType.KEYWORD, match));
            } else if (match.matches(IDENTIFIER_PATTERN)) {
                tokens.add(new Token(TokenType.IDENTIFIER, match));
            } else if (match.matches(INT_LIT_PATTERN)) {
                tokens.add(new Token(TokenType.INT_LITERAL, match));
            } else if (match.matches(CHAR_LIT_PATTERN)) {
                tokens.add(new Token(TokenType.CHAR_LITERAL, match));
            } else if (match.matches(STRING_LIT_PATTERN)) {
                tokens.add(new Token(TokenType.STRING_LITERAL, match));
            } else if (match.matches(OPERATOR_PATTERN)) {
                tokens.add(new Token(TokenType.OPERATOR, match));
            } else if (match.matches(SYMBOL_PATTERN)) {
                tokens.add(new Token(TokenType.SYMBOL, match));
            } else {
                throw new Exception("Error léxico: Token no reconocido " + match);
            }
        }

        // Agregar el token EOF al final
        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }
}
