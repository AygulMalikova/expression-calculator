import AST.Expression;

import java.io.IOException;
import java.io.InputStream;


public class ExpressionCalculator {
    public static void main(String[] args) throws IOException {
        InputStream file = ExpressionCalculator.class.getClassLoader().getResourceAsStream("test1.txt");
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
