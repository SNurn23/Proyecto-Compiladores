package lexer;

public enum TokenType {
    KEYWORD,        // Palabras clave como if, else, int, etc.
    IDENTIFIER,     // Nombres de variables y funciones
    INT_LITERAL,    // Literales enteros
    CHAR_LITERAL,   // Literales de caracteres
    STRING_LITERAL, // Literales de cadenas
    OPERATOR,       // Operadores como +, -, *, /, etc.
    SYMBOL,         // Símbolos como paréntesis, llaves, punto y coma
    COMMENT,        // Comentarios, aunque estos son ignorados en la salida
    EOF             // Fin de archivo
}
