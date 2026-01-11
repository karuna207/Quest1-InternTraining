//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Tokenizer tokenizer= new Tokenizer();
    System.out.println(tokenizer.tokenize("(define x 10)"));
    System.out.println(tokenizer.tokenize("(+ 1 2)"));
    System.out.println(tokenizer.tokenize("(+ 1 (* 2 3))"));
}
