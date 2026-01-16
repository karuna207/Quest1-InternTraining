import nodes.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitors.Evaluator;
import visitors.ExpressionVisitor;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import static org.junit.jupiter.api.Assertions.*;

class LISPInterpreterTest {

    private Parser parser;
    private ExpressionVisitor<Object> visitor;
    @BeforeEach
    void setUp(){
        parser=new Parser();
        visitor=new Evaluator();
    }

    @Test
    void testAdd(){
        String input="(+ 2 5)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals((double)7,result);
    }

    @Test
    void testSub(){
        String input="(- 2 5)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(-3,result);
    }

    @Test
    void testMultiply(){
        String input="(* 2 5)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(10,result);
    }

    @Test
    void testDivision(){
        String input="(/ 2 5)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(0.4,result);
    }

    @Test
    void testNestedExpression(){
        String input="(+ (* 2 3) (- 3 1))";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(8,result);
    }

    @Test
    void testZeroDivision() {
        String input = "(/ 3 0)";
        Node node = parser.parse(input);
        assertThrows(ArithmeticException.class, () -> {
            node.accept(visitor);
        });
    }

    @Test
    void testRelationalOperator(){
        String input="( > 2 5 )";
        Node node=parser.parse(input);
        boolean result= (boolean) node.accept(visitor);
        System.out.println(result);
        assertFalse(result);
    }

    @Test
    void testIfExpression(){
        String input="(if (> 7 3) 2 5)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(2,result);
    }

    @Test
    void testDefine(){
        String input="(define a 3)";
        Node node=parser.parse(input);
        double result=(double)node.accept(visitor);
        assertEquals(3,result);
    }


    }




