import java.util.Scanner;

public class Main {

    public static void Maches(String string) {
        System.out.println(string.matches("for\\s+\\d+\\s*"));
        System.out.println(string.matches("\\s*end\\s+for\\s*"));
        System.out.println(string.matches("\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*"));
        System.out.println(string.matches("\\s*(int|float)\\s*\\w+\\s*"));
        System.out.println(string.matches("[%]+"));
        System.out.println(string.matches("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[+|-]\\s*[\\w|\\d]"));
        System.out.println(string.matches("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]"));
        System.out.println(string.matches("print\\s*[\\w+|\\d]+\\s*"));
        System.out.println(string.matches("\\w+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]+"));
        System.out.println(string.matches("\\s*\\w+\\s*[=]\\s*[\\w|\\d]+\\s*"));
        System.out.println(string.matches("\\w+\\s*[=]\\s*[\\-*\\/+]"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Maches(scanner.nextLine());
    }
}
