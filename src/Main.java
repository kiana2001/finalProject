import java.util.Scanner;


public class Main {

    public static ExpressionType Matches(String string) {

        if (string.matches("for\\s+\\d+\\s*")) {
            return ExpressionType.FOR_HEAD ;
        }

        if (string.matches("\\s*end\\s+for\\s*")) {
            return ExpressionType.FOR_END ;
        }

        if (string.matches("\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*")) {
            return ExpressionType.DEFINED_VAR ;
        }

        if (string.matches("\\s*(int|float)\\s*\\w+\\s*")) {

            return ExpressionType.UNDEFINED_VAR ;
        }
        if (string.matches("%+")) {
            return ExpressionType.DIVIDER ;
        }

        if (string.matches("print\\s*[\\w\\d]+\\s*")) {
            return ExpressionType.PRINT ;
        }

        if (string.matches("^ *[\\w]+\\s*=(\\s*[\\w\\d]+\\s*[\\-*\\/+])*\\s*([\\w\\d]+)$")) {
            return ExpressionType.LOGIC ;
        }


        return null;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       if(Matches(scanner.nextLine())==ExpressionType.FOR_END){

       }
    }
}