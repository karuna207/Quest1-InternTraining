package nodes;

import visitors.ExpressionVisitor;

public class NumberNode implements Node {
    private final Double value;

    public NumberNode(Double val) {
        value = val;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }
}
