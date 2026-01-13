import visitors.Evaluator;
import visitors.ExpressionVisitor;
import nodes.Node;
import visitors.Evaluator;

void main() {

    List<String> program=new ArrayList<>();

    program.add("(+ 1 2)");
    program.add("(* 3 4)");
    program.add("(define x 10)");
    program.add("(if (> x 5) 1 0)");

    ExpressionVisitor<Object> evaluator=new Evaluator();

    Parser p1=new Parser();
    for(String line:program){
        Node node=p1.parse(line);
        Object result=node.accept(evaluator);
        System.out.println("result is "+result);
    }





}
