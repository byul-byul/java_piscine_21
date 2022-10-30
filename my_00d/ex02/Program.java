import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int query = 0;
        int coffeeRequestCount = 0;

        while ((query = scan.nextInt()) != 42) {
            if (isPrimeNum(digitsSum(query))) {
                coffeeRequestCount += 1;
            }
        }
        System.out.println("Count of coffee-request - " + coffeeRequestCount);
    }

    public static int digitsSum(long num) {
        int res = 0;

        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return (res);
    }

    public static boolean isPrimeNum(int num) {
        boolean isPrime = true;

        if (num <= 1) {
            return (false);
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                isPrime = false;
                break ;
            }
            else if (i * i > num) {
                break ;
            }
        }
        return (isPrime);
    }
}
