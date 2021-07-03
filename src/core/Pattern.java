package core;

public class Pattern {
    public static final String[] KEYWORDS = new String[]{
            "int", "float", "for", "end", "print"
    };

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String HIGHLIGHT = "(?<KEYWORD>" + KEYWORD_PATTERN + ")";

    public static final String SPACE = "\\s+";
    public static final String VARIABLE_NAME="\\b([a-zA-Z$][\\w$]*|[_][\\w$]+)\\b";
    public static final String FOR_HEAD = "\\s*(for)\\s+(\\d+)\\s*";
    public static final String FOR_END = "\\s*(end)(\\s+)(for)\\s*";
    public static final String VARIABLE_WITH_AMOUNT = "\\s*(int|float)(\\s*)(\\w+)\\s*([=])\\s*(\\d+(?:\\.\\d+)*)\\s*";
    public static final String VARIABLE_WITHOUT_AMOUNT = "\\s*(int|float)(\\s+)(\\w+)\\s*";
    public static final String DIVIDER = "\\s*[%]+\\s*";
    public static final String MATH_OPERATION = "\\s*([\\w])+\\s*([=])\\s*([\\w|\\d])+\\s*([\\-*\\/+])\\s*([\\w|\\d])\\s*";
    // x = w + 50
    public static final String PRINT = "\\s*(print)\\s+([\\w+|\\d])+\\s*";
    public static final String ASSIGNMENT = "\\s*(\\w+)\\s*([=])\\s*([\\w|\\d]+)\\s*";
}
