import java.io.*;
import java.util.concurrent.atomic.AtomicReference;


public class File {
    public static Parts getType(String data) {
        String FOR_HEAD = "\\s*for\\s*\\d+\\s*";
        String FOR_END = "\\s*end\\s+for\\s*";
        String VARIABLE_WITH_AMOUNT = "\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*";
        String VARIABLE_WITHOUT_AMOUNT = "\\s*(int|float)\\s*\\w+\\s*";
        String DIVIDER = "[%]+";
        String MATH_OPERATION = "[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]";
        String PRINT = "print\\s*[\\w+|\\d]+\\s*";
        String ASSIGNMENT = "\\w+\\s*[=]\\s*[\\w|\\d]+\\s*([\\-*\\/+]\\s*[\\w|\\d])*";
        if (data.matches(Pattern.FOR_HEAD)) return Parts.FOR_HEAD;
        if (data.matches(Pattern.FOR_END)) return Parts.END_FOR;
        if (data.matches(Pattern.VARIABLE_WITH_AMOUNT)) return Parts.DEFINE_VARIABLE_WITH_AMOUNT;
        if (data.matches(Pattern.VARIABLE_WITHOUT_AMOUNT)) return Parts.DEFINE_VARIABLE_WITHOUT_AMOUNT;
        if (data.matches(Pattern.DIVIDER)) return Parts.DIVIDER;
        if (data.matches(Pattern.MATH_OPERATION)) return Parts.MATH_OPERATION;
        if (data.matches(Pattern.PRINT)) return Parts.PRINT;
        if (data.matches(Pattern.ASSIGNMENT)) return Parts.ASSIGNEMNT;

        return null;
    }
   static String data =
            "int x = 10" + "\n" +
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
                    "for 100" + "\n" +
                    "   print num" + "\n" +
                    "   num = num +1" + "\n" +
                    "end for" + "\n" +
                    "for 100" + "\n" +
                    "   print num" + "\n" +
                    "   num = num +1" + "\n" +
                    "end for" + "\n" +
                    "for 100" + "\n" +
                    "   print num" + "\n" +
                    "   num = num +1" + "\n" +
                    "end for";

    public static void main(String[] args) throws IOException {


        Reader reader = new StringReader(data);
        BufferedReader input = new BufferedReader(reader);
        while (true) {
            var line = new AtomicReference<>(input.readLine());
            if (line.get() == null) {
                break;
            }
            getType(data);
            System.out.println(getType(data));
        }
    }
}



