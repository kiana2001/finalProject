import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.atomic.AtomicReference;



public class File {
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
    public static void main(String[] args) throws IOException {
        String data = "  \"abstract\", \"assert\", \"boolean\", \"break\", \"byte\",\n" +
                "            \"case\", \"catch\", \"char\", \"class\", \"const\",\n" +
                "            \"continue\", \"default\", \"do\", \"double\", \"else\",\n" +
                "            \"enum\", \"extends\", \"final\", \"finally\", \"float\",\n" +
                "            \"for\", \"goto\", \"if\", \"implements\", \"import\",\n" +
                "            \"instanceof\", \"int\", \"interface\", \"long\", \"native\",\n" +
                "            \"new\", \"package\", \"private\", \"protected\", \"public\",\n" +
                "            \"return\", \"short\", \"static\", \"strictfp\", \"super\",\n" +
                "            \"switch\", \"synchronized\", \"this\", \"throw\", \"throws\",\n" +
                "            \"transient\", \"try\", \"void\", \"volatile\", \"while\",\"end\",\"print\"";
        Reader reader = new StringReader(data);
        BufferedReader input = new BufferedReader(reader);
        while (true) {
            var line = new AtomicReference<>(input.readLine());
            if(line.get() == null){
                break;
            }
            getType(data);
        }
        System.out.println(getType(data));
    }
}
