public interface ExpressionVisitor<T> {
    public T visitNumber(NumberNode node);
    public T visitSymbol(SymbolNode node);
    public T visitList(ListNode node);
}
