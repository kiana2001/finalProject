import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


public class File {
    public static Parts getType(String data) {

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


    public static void main(String[] args) throws IOException {

        String data = "int x = 10" + "\n" +
                "int y = 30" +"\n" +
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

        Reader reader = new StringReader(data);
        BufferedReader input = new BufferedReader(reader);
        while (true) {
            String line = input.readLine();

            if (line == null) {
                break;
            }
            if(!line.equals(""))
            System.out.println(getType(line));
        }
    }
}



