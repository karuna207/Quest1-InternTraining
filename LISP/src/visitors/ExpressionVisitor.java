package visitors;
import nodes.NumberNode;
import nodes.SymbolNode;
import nodes.ListNode;

public interface ExpressionVisitor<T> {
    public T visitNumber(NumberNode node);
    public T visitSymbol(SymbolNode node);
    public T visitList(ListNode node);
}
