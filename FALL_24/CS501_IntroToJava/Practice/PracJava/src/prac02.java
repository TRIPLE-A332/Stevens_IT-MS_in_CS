
import java.util.Scanner;

public class prac02 {

    public static int factorial(int number){
        int curr = number;
        System.out.print("*");
        for (int i=number; i>0 ; i--){
            
            System.out.print("*" + i);
            curr = i * (i-1);
        }
        return 0;
    }

    public static int Display(int num){
        while(num > 0){
            int r = num % 10;
            num = num/10;
            System.out.println(r);
        }

        return 0;
    }

    public static int Digits(int num){
        int count=0;
        while(num>0){
            num = num/10;
            count++;
        }
        System.out.println(count);
        return 0;
    }

    public static int Armstr(int num){
        int n = num;
        int sum = 0;
        int cube = 0; 
        int r = 0 ;
        while(n>0){
            r = n % 10;
            cube = r*r*r;
            sum += cube;
            n /= 10;
        }
        if (sum == num) {
            System.out.println("arm number");
        }
        else{
            System.out.println("not");
        }
        return 0;
    }

    public static int rev(int num){
        while(num>0){
            int r = num%10;
            int save = r;
            num = num/10;
            System.out.print(r);
        }
        return 0;
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter n ");
        int num = sc.nextInt();

        // words(num);
        //rev(num);
        //Armstr(num);
        //Digits(num);
        // Display(num);
        //factorial(num);
        // Scanner sc = new Scanner(System.in);
        // System.out.println("enter n");
        // int num = sc.nextInt();
        
        // int x = num * (num + 1 )/ 2;
        // System.out.println(x);
        
        
        
        // Scanner sc = new Scanner(System.in);
        // int num = sc.nextInt();
        // int curr = num;
        // for (int i = 0; i <= 10; i++) {
        //     System.out.print(num * i + " ");
        //     curr = num * i;
        // }





        // Scanner sc = new Scanner(System.in);
        // int day = sc.nextInt();

        // switch (day) {
        //     case 1:System.out.println("m");   break;
        //     case 2:System.out.println("t");   break;
        //     case 3:System.out.println("w");   break;
        //     case 4:System.out.println("th");   break;
        //     case 5:System.out.println("f");   break;
        //     case 6:System.out.println("s");   break;
        //     default:
        //         System.out.println("wrong date"); break;
        // }
    }
}
