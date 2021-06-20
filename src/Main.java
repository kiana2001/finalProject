import java.util.Scanner;

@SuppressWarnings("ALL")
public class Main {
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
        String string = scanner.nextLine();
        String s2;
        System.out.println(Maches(scanner.nextLine()));
        switch (string) {
            case "DEFINE_VARIABLE_WITH_AMOUNT":// for 100
                String replacestring4 = string.replaceAll("s*(int|float)(\\s*)(\\w+)\\s*([=])\\s*(\\d+)\\s*","$1$2$3$4$5;");
                break;
            case "DEFINE_VARIABLE_WITHOUT_AMOUNT":
                String replacestring3 = string.replaceAll("\\s*(int|float)(\\s*)(\\w+)\\s*","$1$2$3=0;");
              break;
            case "MATH_OPERATION":
                String replacestring2 = string.replaceAll("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]","$1$2$3$4$5;");
            case "PRINT "://print x >
                String replacestring = string.replaceAll("print\\s*[\\w+|\\d]+\\s*","System.out.$1ln.$2;");
                break;
            case "ASSIGNEMNT":
                String replacestring5 = string.replaceAll("(\\w+)\\s*([=])\\s*([\\w|\\d]+)\\s*([\\-*\\/+]\\s*[\\w|\\d]*)","$1$2$3$4;");
                break;
            case "FOR_HEAD":
                String replacestring6 = string.replaceAll("\\s*(for)\\s*(\\d+)\\s*","$1(int i;i<=100;i++){");
                break;
            case "END_FOR":
                String replacestring7 = string.replaceAll("\\s*(end)(\\s+)(for)\\s*","};");
        }
    }
}
