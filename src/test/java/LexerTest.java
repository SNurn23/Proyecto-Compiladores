import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LexerTest {
    @Test
    public void testFuncion() throws Exception {
        String input = "int main() { int sum = 0; for (int i = 0; i < 5; i++) { sum += i; } return sum; }";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.KEYWORD, "int"),
                new Token(TokenType.IDENTIFIER, "main"),
                new Token(TokenType.SYMBOL, "("),
                new Token(TokenType.SYMBOL, ")"),
                new Token(TokenType.SYMBOL, "{"),
                new Token(TokenType.KEYWORD, "int"),
                new Token(TokenType.IDENTIFIER, "sum"),
                new Token(TokenType.OPERATOR, "="),
                new Token(TokenType.INT_LITERAL, "0"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.KEYWORD, "for"),
                new Token(TokenType.SYMBOL, "("),
                new Token(TokenType.KEYWORD, "int"),
                new Token(TokenType.IDENTIFIER, "i"),
                new Token(TokenType.OPERATOR, "="),
                new Token(TokenType.INT_LITERAL, "0"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.IDENTIFIER, "i"),
                new Token(TokenType.OPERATOR, "<"),
                new Token(TokenType.INT_LITERAL, "5"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.IDENTIFIER, "i"),
                new Token(TokenType.OPERATOR, "++"),
                new Token(TokenType.SYMBOL, ")"),
                new Token(TokenType.SYMBOL, "{"),
                new Token(TokenType.IDENTIFIER, "sum"),
                new Token(TokenType.OPERATOR, "+="),
                new Token(TokenType.IDENTIFIER, "i"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.SYMBOL, "}"),
                new Token(TokenType.KEYWORD, "return"),
                new Token(TokenType.IDENTIFIER, "sum"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.SYMBOL, "}"),
                new Token(TokenType.EOF, "")
        );
        assertTokenListsEqual(expectedTokens, tokens);
    }

    @Test
    public void testMultiplesOperadores() throws Exception {
        String input = "a += b; c == d;";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.IDENTIFIER, "a"),
                new Token(TokenType.OPERATOR, "+="),
                new Token(TokenType.IDENTIFIER, "b"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.IDENTIFIER, "c"),
                new Token(TokenType.OPERATOR, "=="),
                new Token(TokenType.IDENTIFIER, "d"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.EOF, "")
        );

        assertTokenListsEqual(expectedTokens, tokens);
    }

    @Test
    public void testIdentificadoresInvalidos() {
        String input = "int 123invalidName;";
        Lexer lexer = new Lexer(input);

        Exception exception = assertThrows(Exception.class, lexer::tokenize);
        assertTrue(exception.getMessage().contains("Error léxico: Caracter no reconocido en la posición"));
    }

    @Test
    public void testComentarioLinea() throws Exception {
        String input = "// Este es un comentario\nint x = 10;";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.KEYWORD, "int"),
                new Token(TokenType.IDENTIFIER, "x"),
                new Token(TokenType.OPERATOR, "="),
                new Token(TokenType.INT_LITERAL, "10"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.EOF, "")
        );

        assertTokenListsEqual(expectedTokens, tokens);
    }

    @Test
    public void testEspaciosEnBlanco() throws Exception {
        String input = "int    a   =  5;  ";
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        List<Token> expectedTokens = Arrays.asList(
                new Token(TokenType.KEYWORD, "int"),
                new Token(TokenType.IDENTIFIER, "a"),
                new Token(TokenType.OPERATOR, "="),
                new Token(TokenType.INT_LITERAL, "5"),
                new Token(TokenType.SYMBOL, ";"),
                new Token(TokenType.EOF, "")
        );

        assertTokenListsEqual(expectedTokens, tokens);
    }

    private void assertTokenListsEqual(List<Token> expectedTokens, List<Token> actualTokens) {
        assertEquals(expectedTokens.size(), actualTokens.size(), "El número de tokens no coincide");

        for (int i = 0; i < expectedTokens.size(); i++) {
            assertEquals(expectedTokens.get(i).getType(), actualTokens.get(i).getType(), "Tipo de token en la posición " + i + " no coincide");
            assertEquals(expectedTokens.get(i).getValue(), actualTokens.get(i).getValue(), "Valor de token en la posición " + i + " no coincide");
        }
    }
}