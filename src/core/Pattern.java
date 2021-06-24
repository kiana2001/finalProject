package core;

public class Pattern {
    public static String FOR_HEAD="\\s*for\\s*\\d+\\s*";
    public static String FOR_END="\\s*end\\s+for\\s*";
    public static String VARIABLE_WITH_AMOUNT="\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*";
    public static String VARIABLE_WITHOUT_AMOUNT="\\s*(int|float)\\s*\\w+\\s*";
    public static String DIVIDER="[%]+";
    public static String MATH_OPERATION="[\\w|\\d]+\\s*[=]\\s*[\\w|\\d]+\\s*[\\-*\\/+]\\s*[\\w|\\d]";
    public static String PRINT="print\\s*[\\w+|\\d]+\\s*";
    public static String ASSIGNMENT ="\\w+\\s*[=]\\s*[\\w|\\d]+\\s*([\\-*\\/+]\\s*[\\w|\\d])*";

    private static final String[] KEYWORDS = new String[] {
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while","end","print"
    };

    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/"   // for whole text processing (text blocks)
            + "|" + "/\\*[^\\v]*" + "|" + "^\\h*\\*([^\\v]*|/)";  // for visible paragraph processing (line by line)
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String HIGHLIGHT =  "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<PAREN>" + PAREN_PATTERN + ")"
            + "|(?<BRACE>" + BRACE_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")";
}
