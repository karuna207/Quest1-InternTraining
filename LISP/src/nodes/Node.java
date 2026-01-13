package nodes;

import visitors.ExpressionVisitor;

public interface Node {

    public <T> T accept(ExpressionVisitor<T> visitor);

}
