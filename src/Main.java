import javax.lang.model.element.ElementKind;
import java.util.Scanner;

public class Main {
    public static int count = 1;


    public static Enum Maches(String string) {
        if (string.matches("\\s*for\\s*\\d+\\s*")) return Parts.FOR_HEAD;
        if (string.matches("\\s*end\\s+for\\s*")) return Parts.END_FOR;
        if (string.matches("\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*")) return Parts.DEFINE_VARIABLE_WITH_AMOUNT;
        if (string.matches("\\s*(int|float)\\s*\\w+\\s*")) return Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT;
        if (string.matches("[%]+")) {
            return Parts.DIVIDER;
        }
        if (string.matches("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]"))
            return Parts.MATH_OPERATION;
        if (string.matches("print\\s*[\\w+|\\d]+\\s*"))
            return Parts.PRINT;
        if (string.matches("\\w+\\s*[=]\\s*[\\w|\\d]+\\s*([\\-*\\/+]\\s*[\\w|\\d])*")) {
            return Parts.ASSIGNEMNT;
        }

        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Maches(scanner.nextLine()));
    }
}
