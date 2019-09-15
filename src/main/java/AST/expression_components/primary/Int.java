package AST.expression_components.primary;
import AST.expression_components.Primary;

public class Int extends Primary {
    private int value;

    public Int(int res) {
        value = res;
    }

    @Override
    public int calculate() {
        return value;
    }
}
