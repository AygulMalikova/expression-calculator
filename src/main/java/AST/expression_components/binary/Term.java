package AST.expression_components.binary;

import AST.Expression;
import AST.expression_components.Binary;

public class Term extends Binary {
    public Term(Expression left, Expression right) {
        super(left, right);
    }
}
