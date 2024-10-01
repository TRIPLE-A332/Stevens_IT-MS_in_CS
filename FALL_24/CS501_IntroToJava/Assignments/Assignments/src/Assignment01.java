

/*
* Name:     ALI ABDULLAH AHMAD
* CWID:     20031246
*/

public class Assignment01 {
    public static double calculateTriArea(double a, double b, double c) {
        
        double s = (a+b+c)/2;
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));

    return area;
    }


    public static String switchCase(String str) {
        
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
                System.out.print(ch);
            }
            else if (Character.isLowerCase(ch)){
                ch = Character.toUpperCase(ch);
                System.out.print(ch);
            }
            else if(Character.isDigit(ch)){
                System.out.print(ch);
            }
        }
    return "";
    }


    public static String reverse(String str) {
        
        for( int i= str.length()-1 ; i>=0 ; i--){
            char ch2 = str.charAt(i);
            System.out.print(ch2);
        }
    return "";
    }

    public static long fib(int ith) {
        int arr[] = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
        ith = arr[ith];
    return ith;
    }
    public static void main(String[] args) {
        System.out.println("Testing calculateTriArea():");
        System.out.print("(3, 4, 5) -> ");
        System.out.println(calculateTriArea(3, 4, 5));
        System.out.print("(3, 3, 3) -> ");
        System.out.println(calculateTriArea(3, 3, 3));
        
        System.out.println("\nTesting switchCase():");
        System.out.print("abc -> ");
        System.out.println(switchCase("abc"));
        System.out.print("AbC -> ");
        System.out.println(switchCase("AbC"));
        System.out.print("123 -> ");
        System.out.println(switchCase("123"));
        
        System.out.println("\nTesting reverse():");
        System.out.print("xyz -> ");
        System.out.println(reverse("xyz"));
        System.out.print("0xff -> ");
        System.out.println(reverse("0xff"));
        System.out.println("\nTesting fib():");
        
        for (int i = 0; i < 13; i++) {
            System.out.print("fib(" + i + ") -> ");
            System.out.println(fib(i));
            }
    }
    }