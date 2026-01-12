//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Parser p1=new Parser();
    Node node=p1.parse("(+ 1 2)");

    ExpressionVisitor<String> evaluator=new Evaluator();
    String result=node.accept(evaluator);

    System.out.println("result "+result);

}
