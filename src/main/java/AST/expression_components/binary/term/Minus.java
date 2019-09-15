package AST.expression_components.binary.term;
import AST.Expression;
import AST.expression_components.binary.Term;

public class Minus extends Term {
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int calculate() {
        return left.calculate() - right.calculate();
    }
}
