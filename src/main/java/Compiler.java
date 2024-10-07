import lexer.Lexer;
import lexer.Token;

import java.util.List;

public class Compiler {
    public static void main(String[] args) {
        String input = "int main() {\n"
                + "    int sum = 0;\n"
                + "    for (int i = 0; i < 5; i++) {\n"
                + "        sum += i;\n"
                + "    }\n"
                + "    return sum;\n"
                + "}\n";


        Lexer lexer = new Lexer(input);  // Inicializar el lexer con el código de entrada
        try {
            List<Token> tokens = lexer.tokenize();  // Tokenizar el código

            // Imprimir los tokens generados
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());  // Mostrar errores léxicos
        }
    }
}
