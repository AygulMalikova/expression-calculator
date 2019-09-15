package AST.expression_components.binary;
import AST.Expression;
import AST.expression_components.Binary;

public class Factor extends Binary {
    public Factor(Expression left, Expression right) {
        super(left, right);
    }
}
