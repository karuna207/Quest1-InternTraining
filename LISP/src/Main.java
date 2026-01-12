//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    List<String> program=new ArrayList<>();

    program.add("(define x 10)");
    program.add("(if (> x 5) 1 0)");

    ExpressionVisitor<Object> evaluator=new Evaluator();

    Parser p1=new Parser();
    for(String line:program){
        Node node=p1.parse(line);
        String result=node.accept(evaluator);
        System.out.println("result "+result);
    }





}
