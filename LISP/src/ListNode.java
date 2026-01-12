import java.util.List;

public class ListNode implements Node {
    private List<Node> childrenNodes;
    public ListNode(List<Node>children){
        childrenNodes=children;
    }
    public List<Node> getChildren(){
        return childrenNodes;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitList(this);
    }
}
