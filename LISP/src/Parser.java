import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> tokens;
    private final Tokenizer tokenizer;
    private int position;

    public Parser(){
        tokenizer=new Tokenizer();
    }
    public Node parse(String input){
        tokens=tokenizer.tokenize(input);
        position=0;
        return parseExpression();
    }

    private Node parseExpression(){
        String current_token= tokens.get(position);

        if(current_token.equals("(")){
            List<Node> nodes=new ArrayList<>();
            position++;
            while(position<tokens.size() && !tokens.get(position).equals(")")){
                Node node=parseExpression();
                nodes.add(node);
            }

            position++;
            return NodeFactory.createListNode(nodes);
        }
        else if(current_token.matches("-?\\d+")){
            position++;
            return NodeFactory.createNumberNode(Integer.parseInt(current_token));
        }else{
            position++;
            return NodeFactory.createSymbolNode(current_token);
        }
    }

}
