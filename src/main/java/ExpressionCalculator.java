import AST.Expression;

import java.io.IOException;
import java.io.InputStream;


public class ExpressionCalculator {
    private static final char EOF = (char) -1;

    public static void main(String[] args) throws IOException {
        InputStream file = ExpressionCalculator.class.getClassLoader().getResourceAsStream("test4.txt");

        Parser parser = new Parser(file);
        Expression parsedExpr = parser.parse();
        if (parsedExpr == null) {
            System.out.println("Wrong input");
        }
        else {
            System.out.println(String.valueOf(parsedExpr.calculate()));
        }
    }
}
