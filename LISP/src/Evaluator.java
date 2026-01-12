import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator implements ExpressionVisitor<Object> {
    private GlobalEnvironment env=GlobalEnvironment.getInstance();
    @Override
    public Object visitNumber(NumberNode node) {
        return node.getValue();
    }

    @Override
    public Object visitSymbol(SymbolNode node) {
        String symbol=node.getSymbol();

        if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals(">") || symbol.equals("<") ||
                symbol.equals(">=") || symbol.equals("<=") ||
                symbol.equals("==")){
            return symbol;
        }
        return env.getVariable(symbol).toString();
    }

    @Override
    public Object visitList(ListNode node) {
         List<Node> nodes=node.getChildren();
         Node symbolNode=nodes.get(0);
         Node leftNode=nodes.get(1);
         Node rightNode=nodes.get(2);

         if(symbolNode instanceof SymbolNode && ((SymbolNode) symbolNode).getSymbol().equals("define")){
             String varName = ((SymbolNode) leftNode).getSymbol();
             Object value=rightNode.accept(this);
             env.addVariable(varName,value);
             return value;
         }
         Object symbol=symbolNode.accept(this);
         Object leftNumber=leftNode.accept(this);
         Object rightNumber=rightNode.accept(this);
         if(symbol.equals("+")){
             return (int)leftNumber+(int)rightNumber;
         }
         else if(symbol.equals("-")){
             return (int)leftNumber-(int)rightNumber;
         }else if(symbol.equals("*")){
             return (int)leftNumber * (int)rightNumber;
         }else if(symbol.equals("/")){
             if((int)rightNumber==0){
                 throw new ArithmeticException("Division by zero is not possible");
             }
             return (int)leftNumber/(int)rightNumber;
         }
         return "";
    }
}
