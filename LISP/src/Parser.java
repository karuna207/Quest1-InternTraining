import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> tokens;
    private Tokenizer tokenizer;
    private int position;

    public Parser(String input){
        tokenizer=new Tokenizer();
        tokens=tokenizer.tokenize(input);
        position=0;
        parseExpression();
    }

    private Node parseExpression(){
        String current_token= tokens.get(position);

        if(current_token.equals("(")){
            List<Node> nodes=new ArrayList<>();
            position++;
            while(!tokens.get(position).equals(")")&& position< tokens.size()){
                Node node=parseExpression();
                nodes.add(node);
            }

            position++;
            return NodeFactory.createListNode(nodes);
        }
        else if(current_token.matches("-?\\d+")){
            return NodeFactory.createNumberNode(Integer.parseInt(current_token));
        }else{
            return NodeFactory.createSymbolNode(current_token);
        }
    }

}
