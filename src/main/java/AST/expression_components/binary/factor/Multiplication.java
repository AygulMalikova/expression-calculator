package AST.expression_components.binary.factor;
import AST.Expression;
import AST.expression_components.binary.Term;

public class Multiplication extends Term {
    public Multiplication(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int calculate() {
        return left.calculate() * right.calculate();
    }
}
