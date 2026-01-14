import visitors.Evaluator;
import visitors.ExpressionVisitor;
import nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LISPInterpreter {
    public static void main() {

        ExpressionVisitor<Object> evaluator = new Evaluator();
        Parser p1 = new Parser();
        String input = getInputFromUser();
        Node node = p1.parse(input);
        Object result = node.accept(evaluator);
        System.out.println("result is " + result);

    }

    private static String getInputFromUser() {
        System.out.println("Enter input expression:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
