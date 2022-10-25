import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner     scan = new Scanner(System.in);
        int         num = scan.nextInt();
        int         checkCount = 0;
        boolean     isPrime = true;

        if (num <= 1) {
            System.err.println("theIllegalArgument");
            System.exit(-1);
        }
        for (int i = 2; i < num; i++) {
            checkCount += 1;
            if (num % i == 0) {
                isPrime = false;
                break ;
            }
        }
        System.out.println(isPrime + " " + checkCount);
    }
}