package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Random;

public class Interpreter {

    public static Parts getType(String string) {
        if (string.matches(Pattern.FOR_HEAD)) return Parts.FOR_HEAD;
        if (string.matches(Pattern.FOR_END)) return Parts.END_FOR;
        if (string.matches(Pattern.VARIABLE_WITH_AMOUNT)) return Parts.DEFINE_VARIABLE_WITH_AMOUNT;
        if (string.matches(Pattern.VARIABLE_WITHOUT_AMOUNT)) return Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT;
        if (string.matches(Pattern.DIVIDER)) return Parts.DIVIDER;
        if (string.matches(Pattern.MATH_OPERATION)) return Parts.MATH_OPERATION;
        if (string.matches(Pattern.PRINT)) return Parts.PRINT;
        if (string.matches(Pattern.ASSIGNMENT))  return Parts.ASSIGNEMNT;

        return null;
    }

    private static String randString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String Changer(String string) {

        Parts type = Interpreter.getType(string);

        if (Parts.DEFINE_VARIABLE_WITH_AMOUNT.equals(type)) {
            return string.replaceAll(Pattern.VARIABLE_WITH_AMOUNT, "$1$2$3$4($1)$5;");

        } else if (Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT.equals(type)) {
            return  string.replaceAll(Pattern.VARIABLE_WITHOUT_AMOUNT, "$1$2$3=($1)0;");

        } else if (Parts.MATH_OPERATION.equals(type)) {//w=w+y
            return string.replaceAll(Pattern.MATH_OPERATION, "$1$2$3$4$5;");

        } else if (Parts.PRINT.equals(type)) {
            return string.replaceAll(Pattern.PRINT, "System.out."+"$1"+"ln."+"($2);");

        } else if (Parts.ASSIGNEMNT.equals(type)) {
            return string.replaceAll(Pattern.ASSIGNMENT, "$1$2$3$4;");

        } else if (Parts.FOR_HEAD.equals(type)) {
            String randomString=randString();
            return string.replaceAll(Pattern.FOR_HEAD, "$1" + "(int " + randomString + ";" +randomString +"<="+"$2;" + randomString + "++){");
        } else if (Parts.END_FOR.equals(type)) {
            return string.replaceAll(Pattern.FOR_END, "}");

        }else if (Parts.DIVIDER.equals(type)) {
            return "\n";

        }

        return null;
    }
    public static boolean toFile(String path) throws IOException {
        String data = "int x = 10" + "\n" +
                "int y = 30" + "\n" +
                "int z" + "\n" +
                "int num = 1" + "\n" +
                "" + "\n" +
                "%%" + "\n" +
                "" + "\n" +
                "for 100" + "\n" +
                "   print num" + "\n" +
                "   num = num +1" + "\n" +
                "end for" + "\n" +
                "for 60" + "\n" +
                "   print num" + "\n" +
                "   num = num +1" + "\n" +
                "end for" + "\n" +
                "for 4" + "\n" +
                "   print num" + "\n" +
                "   num = num +1" + "\n" +
                "end for" + "\n" +
                "for 100" + "\n" +
                "   print num" + "\n" +
                "   num = num +1" + "\n" +
                "end for";

        Reader reader = new StringReader(data);
        BufferedReader input = new BufferedReader(reader);
        while (true) {
            String line = input.readLine();
            if (line == null) {
                break;
            }
            if (!line.equals("")){
                System.out.println(Interpreter.Changer(line));
            }

        }
        return true;
    }
}
