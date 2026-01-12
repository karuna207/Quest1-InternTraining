import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator implements ExpressionVisitor<String> {
    private GlobalEnvironment env=GlobalEnvironment.getInstance();
    @Override
    public String visitNumber(NumberNode node) {
        return String.valueOf(node.getValue());
    }

    @Override
    public String visitSymbol(SymbolNode node) {
        String symbol=node.getSymbol();

        if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")){
            return symbol;
        }
        return env.getVariable(symbol).toString();
    }

    @Override
    public String visitList(ListNode node) {
         List<Node> nodes=node.getChildren();
         Node symbolNode=nodes.get(0);
         Node leftNode=nodes.get(1);
         Node rightNode=nodes.get(2);

         if(symbolNode instanceof SymbolNode && ((SymbolNode) symbolNode).getSymbol().equals("define")){
             String varName = ((SymbolNode) leftNode).getSymbol();
             String value=rightNode.accept(this);
             env.addVariable(varName,value);
             return value;
         }
         String symbol=symbolNode.accept(this);
         String leftNumber=leftNode.accept(this);
         String rightNumber=rightNode.accept(this);
         if(symbol.equals("+")){
             return String.valueOf(Integer.parseInt(leftNumber)+Integer.parseInt(rightNumber));
         }
         else if(symbol.equals("-")){
             return String.valueOf(Integer.parseInt(leftNumber)-Integer.parseInt(rightNumber));
         }else if(symbol.equals("*")){
             return String.valueOf(Integer.parseInt(leftNumber)*Integer.parseInt(rightNumber));
         }else if(symbol.equals("/")){
             return String.valueOf(Integer.parseInt(leftNumber)/Integer.parseInt(rightNumber));
         }
         return "";
    }
}
