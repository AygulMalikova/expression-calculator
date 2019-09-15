package AST.expression_components.binary.relation;

import AST.Expression;
import AST.expression_components.binary.Relation;

public class Greater extends Relation {
    public Greater(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int calculate()
    {
        return left.calculate() > right.calculate() ? 1 : 0 ;
    }

}
