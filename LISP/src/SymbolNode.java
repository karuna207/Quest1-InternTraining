public class SymbolNode implements Node {
    private String symbol;

    public SymbolNode(String symbolValue){
        symbol=symbolValue;
    }
    public String getSymbol(){
        return symbol;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitSymbol(this);
    }
}
