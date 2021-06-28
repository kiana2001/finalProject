package core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        if (string.matches(Pattern.ASSIGNMENT)) return Parts.ASSIGNMENT;

        return null;
    }

    private static String randString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'zh'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String Changer(String string) {

        Parts type = Interpreter.getType(string);

        if (Parts.DEFINE_VARIABLE_WITH_AMOUNT.equals(type)) {
            return string.replaceAll(Pattern.VARIABLE_WITH_AMOUNT, "$1$2$3$4($1)$5;");

        } else if (Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT.equals(type)) {
            return string.replaceAll(Pattern.VARIABLE_WITHOUT_AMOUNT, "$1$2$3=($1)0;");

        } else if (Parts.MATH_OPERATION.equals(type)) {//w=w+y
            return string.replaceAll(Pattern.MATH_OPERATION, "$1$2$3$4$5;");

        } else if (Parts.PRINT.equals(type)) {
            return string.replaceAll(Pattern.PRINT, "System.out." + "$1" + "ln($2);");

        } else if (Parts.ASSIGNMENT.equals(type)) {
            return string.replaceAll(Pattern.ASSIGNMENT, "$1$2$3$4;");

        } else if (Parts.FOR_HEAD.equals(type)) {
            String randomString = randString();
            return string.replaceAll(Pattern.FOR_HEAD, "$1" + "(int " + randomString + "=0;" + randomString + "<=" + "$2;" + randomString + "++){");
        } else if (Parts.END_FOR.equals(type)) {
            return string.replaceAll(Pattern.FOR_END, "}");

        } else if (Parts.DIVIDER.equals(type)) {
            return "\n";

        }

        return null;
    }

    public static List<Integer> x2java(String x, String path) throws IOException {
        List<Integer> errors = new ArrayList<>();
        int lineNum = 1;

        BufferedReader sc = new BufferedReader(new StringReader(x));
        StringBuilder data= new StringBuilder();

        String line;
        while ((line = sc.readLine()) != null) {

            if (getType(line) == null && !line.equals(""))
                errors.add(lineNum);
            else if(getType(line)!=null){
                data.append(Interpreter.Changer(line));
                data.append("\n");
            }
            lineNum++;
        }
        if (errors.isEmpty()) {
            File interpreted = new File(path + "\\Interpreted.java");
            var result=toFile(interpreted,data.toString());
            if(!result)
                errors.add(-1);
        }
        return errors;

    }

    public static boolean toFile(File interpreted, String data) {

        boolean result = true;
        String layout= """
                public class Interpreted {
                     public static void main(String[] args) {
                       """+ data + """
                }
                }""";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(interpreted)))) {
            out.print(layout);
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
