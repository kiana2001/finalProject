public class Main {

    public static void Maches() {
        String Str = "for 100 ";
        String Str1 = "end   for";
        String Str2 = "int x =10  ";
        System
        System.out.println(Str.matches("for\\s+\\d+\\s+"));
        System.out.println(Str1.matches("\\s*end\\s+for\\s*"));
        System.out.println(Str2.matches("\\s*(int|float)\\s*\\w+\\s*[=]\\s*\\d+\\s*"));
    }

    public static void main(String[] args) {
        Maches();
    }
}
