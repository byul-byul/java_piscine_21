import java.util.Scanner;

class Main {

    private static final int        MAX_WEEK = 18;
    private static final String     EOF = "42";
    private static final String     TITLE = "Week ";
    private static final int        ARG_COUNT = 5;


    public static void  main(String[] args) {

        Scanner     scan = new Scanner(System.in);
        String      inputLine = "";
        int         weekCount = 0;
        long        minMarksRow = 0;

        while (!((inputLine = scan.nextLine()).equals(EOF)) && weekCount <= MAX_WEEK) {
            if (inputLine.equals(TITLE + (weekCount + 1))) {
                int         mul = 1;
                int         mark = 0;
                String      marks = scan.nextLine();
                Scanner     scanM = new Scanner(marks);

                for (int i = 0; i < ARG_COUNT; i++) {
                    mark = scanM.nextInt();
                    if (mark >= 10 || mark < 1) {
                        System.out.println("IllegalArgument");
                        System.exit(-1);
                    }
                    minMarksRow += mark * mul;
                    mul *= 10;
                }
                System.out.println(minMarksRow);
                minMarksRow = 0;
            }
            else {
                System.out.println("IllegalArgument--");
                System.exit(-1);
            }
            weekCount += 1;
            System.out.println(weekCount);
        }
        while (minMarksRow != 0) {
            int     
        }
    }
}