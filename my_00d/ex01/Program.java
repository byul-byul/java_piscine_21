import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner     scan = new Scanner(System.in);
        int         num = scan.nextInt();
        int         checkCount = 0;
        boolean     isPrime = true;

        if (num <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        for (int i = 2; i < num; i++) {
            checkCount += 1;
            if (num % i == 0) {
                isPrime = false;
                break ;
            }
            else if (i * i > num) {
                break ;
            }
        }
        if (num == 2) {
            checkCount++;
        }
        System.out.println(isPrime + " " + checkCount);
    }
}
