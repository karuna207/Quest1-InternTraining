public interface Node {

    public <T> T accept(ExpressionVisitor<T> visitor);

}
