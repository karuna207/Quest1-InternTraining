import java.util.ArrayList;
import java.util.List;


public class Tokenizer {
    public List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(0) != '(' || input.charAt(input.length() - 1) != ')') {
                throw new RuntimeException("Invalid Expression: Expression should begin with ( and end with )");
            }
            char ch = input.charAt(i);
            if (ch == '(') {
                tokens.add("(");
            } else if (ch == ')') {
                tokens.add(")");
            } else if (Character.isDigit(ch) || ch == '-' && i + 1 < input.length() && Character.isDigit(input.charAt(i + 1))) {
                StringBuilder number = new StringBuilder();

                if (ch == '-') {
                    number.append(ch);
                    i++;
                }

                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    number.append(input.charAt(i));
                    i++;
                }
                i--;

                tokens.add(String.valueOf(number));
            } else if (Character.isWhitespace(ch)) {
                continue;
            } else {
                StringBuilder symbol = new StringBuilder();

                while (i < input.length() && !Character.isWhitespace(input.charAt(i))
                        && input.charAt(i) != '(' && input.charAt(i) != ')') {
                    symbol.append(input.charAt(i));
                    i++;
                }
                i--;

                tokens.add(String.valueOf(symbol));
            }
        }

        return tokens;

    }
}
