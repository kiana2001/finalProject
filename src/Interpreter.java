public class Interpreter {
    public static Enum Maches(String string) {
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
}
