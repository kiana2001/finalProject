package core;

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
        //System.out.println(Maches(scanner.nextLine()));

        Enum maches = Maches(string);// for 100
//print x >
        if (Parts.DEFINE_VARIABLE_WITH_AMOUNT.equals(maches)) {
            String replacestring4 = string.replaceAll("s*(int|float)(\\s*)(\\w+)\\s*([=])\\s*(\\d+)\\s*", "$1$2$3$4$5;");
            System.out.println(replacestring4);
        } else if (Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT.equals(maches)) {
            String replacestring3 = string.replaceAll("\\s*(int|float)(\\s*)(\\w+)\\s*", "$1$2$3=0;");
            System.out.println(replacestring3);
        } else if (Parts.MATH_OPERATION.equals(maches)) {
            String replacestring2 = string.replaceAll("[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]", "$1$2$3$4$5;");
            System.out.println(replacestring2);
        } else if (Parts.PRINT.equals(maches)) {
            String replacestring = string.replaceAll("print\\s*[\\w+|\\d]+\\s*", "System.out.$1ln.$2;");
            System.out.println(replacestring);
        } else if (Parts.ASSIGNEMNT.equals(maches)) {
            String replacestring5 = string.replaceAll("(\\w+)\\s*([=])\\s*([\\w|\\d]+)\\s*([\\-*\\/+]\\s*[\\w|\\d]*)", "$1$2$3$4;");
            System.out.println(replacestring5);
        } else if (Parts.FOR_HEAD.equals(maches)) {
            String replacestring6 = string.replaceAll("\\s*(for)\\s*(\\d+)\\s*", "$1(int i;i<=100;i++){");
            System.out.println(replacestring6);
        } else if (Parts.END_FOR.equals(maches)) {
            String replacestring7 = string.replaceAll("\\s*(end)(\\s+)(for)\\s*", "};");
            System.out.println(replacestring7);
        }
    }
}
