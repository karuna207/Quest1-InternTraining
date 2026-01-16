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
    private GlobalEnvironment env = GlobalEnvironment.getInstance();

    @Override
    public Object visitNumber(NumberNode node) {
        return node.getValue();
    }

    @Override
    public Object visitSymbol(SymbolNode node) {
        String symbol = node.getSymbol();

        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals(">") || symbol.equals("<") ||
                symbol.equals(">=") || symbol.equals("<=") || symbol.equals("==") || symbol.equals("!=")) {
            return symbol;
        }
        return env.getVariable(symbol);
    }

    @Override
    public Object visitList(ListNode node) {
        List<Node> nodes = node.getChildren();
        Node symbolNode = nodes.get(0);


        if (isIfExpression(symbolNode)) {
            return handleIfExpression(nodes);

        }

        if (isDefineExpression(symbolNode)) {
            return handleDefineExpression(nodes);
        }
        Double result = (double) 0;
        Object symbol = symbolNode.accept(this);

        if (isArithmeticOperator(symbol)) {
            result = handleArithmethicOperator((String) symbol, nodes);
            return result;

        } else {
            double leftNumber = (double) nodes.get(1).accept(this);
            double rightNumber = (double) nodes.get(2).accept(this);

            return handleRelationOperator((String) symbol, leftNumber, rightNumber);

        }

    }

    private boolean isArithmeticOperator(Object symbol) {
        return Arrays.asList("+", "-", "*", "/").contains(symbol);
    }

    private boolean handleRelationOperator(String symbol, double leftNumber, double rightNumber) {
        switch ((String) symbol) {
            case ">":
                return leftNumber > rightNumber;
            case "<":
                return leftNumber < rightNumber;
            case ">=":
                return leftNumber >= rightNumber;
            case "<=":
                return leftNumber <= rightNumber;
            case "==":
                return leftNumber == rightNumber;
            case "!=":
                return leftNumber != rightNumber;
        }
        return false;
    }

    private Double handleArithmethicOperator(String symbol, List<Node> nodes) {
        Double result = (double)0;
        int childrenCount = nodes.size();
        for (int i = 1; i < childrenCount; i++) {
            Node Childnode = nodes.get(i);
            double number = (double)Childnode.accept(this);

            switch ((String) symbol) {
                case "+":
                    result += number;
                    break;

                case "-":
                    if(i==1){
                        result=(double)number;
                    }else{
                        result -= number;
                    }
                    break;

                case "*":
                    if (i == 1) {
                        result = (double)1;
                    }
                    result *= number;
                    break;

                case "/":
                    if (i >= 2 && number == 0) {
                        throw new ArithmeticException("Division by zero is not possible");
                    }
                    if (i == 1) {
                        result = (double)number;
                        continue;
                    }
                    result /= number;
                    break;
            }

        }
        return result;
    }

    private boolean isIfExpression(Node symbolNode) {
        return symbolNode instanceof SymbolNode
                && ((SymbolNode) symbolNode).getSymbol().equals("if");
    }

    private boolean isDefineExpression(Node symbolNode) {
        return symbolNode instanceof SymbolNode
                && ((SymbolNode) symbolNode).getSymbol().equals("define");
    }

    private Object handleIfExpression(List<Node> nodes) {
        if (nodes.size() < 4) {
            throw new RuntimeException("If expression is invalid");
        }
        Node condition = nodes.get(1);
        Object boolResult = condition.accept(this);

        if (!(boolResult instanceof Boolean)) {
            throw new RuntimeException("Condition must evaluate to boolean");
        }

        if ((boolean) boolResult) {
            return nodes.get(2).accept(this);
        } else {
            return nodes.get(3).accept(this);
        }

    }

    private Object handleDefineExpression(List<Node> nodes) {
        String varName = ((SymbolNode) nodes.get(1)).getSymbol();
        Object value = nodes.get(2).accept(this);
        env.addVariable(varName, value);
        return value;
    }
}
