public class Main {

    public static void  Maches(){
        String Str = "for 6 ";

        System.out.print("Does String contains regex for\\s\\d ? : " );
        System.out.println(Str.matches("for\\s\\d\\s"));

    }
    public static void main(String[] args) {
        Maches();
    }
}
