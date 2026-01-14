package visitors;
import globalenvironment.GlobalEnvironment;
import nodes.Node;
import nodes.NumberNode;
import nodes.SymbolNode;
import nodes.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator implements ExpressionVisitor<Object> {
    private GlobalEnvironment env= GlobalEnvironment.getInstance();
    @Override
    public Object visitNumber(NumberNode node) {
        return node.getValue();
    }

    @Override
    public Object visitSymbol(SymbolNode node) {
        String symbol=node.getSymbol();

        if(symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals(">") || symbol.equals("<") ||
                symbol.equals(">=") || symbol.equals("<=") || symbol.equals("==") ||  symbol.equals("!=")){
            return symbol;
        }
        return env.getVariable(symbol);
    }

    @Override
    public Object visitList(ListNode node) {
         List<Node> nodes=node.getChildren();
         Node symbolNode=nodes.get(0);
         Node leftNode=nodes.get(1);
         Node rightNode=nodes.get(2);

         if(symbolNode instanceof SymbolNode && ((SymbolNode) symbolNode).getSymbol().equals("if")){
             if(nodes.size()<4){
                 throw new RuntimeException("If expression is invalid");
             }
             Node condition=nodes.get(1);
             Object bool_result=condition.accept(this);

             if(!(bool_result instanceof Boolean)){
                throw new RuntimeException("Condition must evaluate to boolean");
             }

             if((boolean) bool_result){
                 return nodes.get(2).accept(this);
             }else{
                return nodes.get(3).accept(this);
             }

         }

         if(symbolNode instanceof SymbolNode && ((SymbolNode) symbolNode).getSymbol().equals("define")){
             String varName = ((SymbolNode) leftNode).getSymbol();
             Object value=rightNode.accept(this);
             env.addVariable(varName,value);
             return value;
         }
        int result=0;
        Object symbol=symbolNode.accept(this);
         if(isArithmeticOperator(symbol)){

             int childrenCount=nodes.size();
             for(int i=1;i<childrenCount;i++){
                 Node Childnode=nodes.get(i);
                 int number=(int)Childnode.accept(this);

                 if(symbol.equals("+")){
                     result+=number;
                 }
                 else if(symbol.equals("-")){
                     result-=number;
                 }else if(symbol.equals("*")){
                     if(i==1){
                         result=1;
                     }
                     result*=number;
                 }else if(symbol.equals("/")){
                     if(i==2 && number==0){
                         throw new ArithmeticException("Division by zero is not possible");
                     }
                     if(i==1){
                         result=number;
                         continue;
                     }
                     result/=number;
                 }
             }
         }else{
             int leftNumber=(int)nodes.get(1).accept(this);
             int rightNumber=(int)nodes.get(2).accept(this);

             if(symbol.equals(">")){
                 return leftNumber>rightNumber;
             }else if(symbol.equals("<")){
                 return leftNumber<rightNumber;
             }
             else if(symbol.equals(">=")){
                 return leftNumber>=rightNumber;
             }
             else if(symbol.equals("<=")){
                 return leftNumber<=rightNumber;
             }else if(symbol.equals("==")){
                 return leftNumber==rightNumber;
             }else if(symbol.equals("!=")){
                 return leftNumber!=rightNumber;
             }
         }

         return result;
    }

    private boolean isArithmeticOperator(Object symbol) {
        return Arrays.asList("+", "-", "*", "/").contains(symbol);
    }


}
