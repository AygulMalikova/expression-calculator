import static org.junit.jupiter.api.Assertions.assertEquals;

import AST.Expression;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ExpressionCalculatorTest {
    private String fileName;

    @Test
    public void test1() throws IOException {
        fileName = "test1.txt";
        assertEquals("1", result());
    }

    @Test
    public void test2() throws IOException {
        fileName = "test2.txt";
        assertEquals("24", result());
    }

    @Test
    public void test3() throws IOException {
        fileName = "test3.txt";
        assertEquals("-1029117443", result());
    }

    @Test
    public void test4() throws IOException {
        fileName = "test4.txt";
        assertEquals("Wrong input", result());
    }

    private String result() throws IOException {
        Parser parser = new Parser(
                ExpressionCalculatorTest.class
                .getClassLoader()
                .getResourceAsStream(fileName));
        Expression parsedExpr = parser.parse();
        if (parsedExpr == null) {
            return "Wrong input";
        }
        else {
            return String.valueOf(parsedExpr.calculate());
        }
    }

}