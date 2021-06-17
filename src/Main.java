import java.util.Scanner;

public class Main {
    public static int count=1;

    public static void Maches(String string) {

        if (string.matches("for\\s+\\d+\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }

        if (string.matches("\\s*end\\s+for\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\s*(int|float)\\s*\\w+\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("[%]+")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[+|-]\\s*[\\w|\\d]")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("print\\s*[\\w+|\\d]+\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\w+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]+")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\s*\\w+\\s*[=]\\s*[\\w|\\d]+\\s*")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\w+\\s*[=]\\s*[\\-*\\/+]")) {
            System.out.println(true);
            System.out.println(count);
        } else {
            count++;
        }
        if (string.matches("\\w+\\s*[=]\\s*[\\-*\\/+]")) {
            System.out.println(true);
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maches(scanner.nextLine());
    }
}
