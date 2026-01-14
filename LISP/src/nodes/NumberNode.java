package nodes;

import visitors.ExpressionVisitor;

public class NumberNode implements Node {
    private int value;

    public NumberNode(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }
}
