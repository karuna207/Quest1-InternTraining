package nodes;
import nodes.ListNode;
import nodes.Node;
import nodes.SymbolNode;
import nodes.NumberNode;
import java.util.List;

public class NodeFactory {
    public static NumberNode createNumberNode(int val){
        return new NumberNode(val);
    }
    public static SymbolNode createSymbolNode(String token){
        return new SymbolNode(token);
    }
    public static ListNode createListNode(List<Node> nodeList){
        return new ListNode(nodeList);
    }
}
