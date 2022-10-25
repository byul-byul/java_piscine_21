import java.util.Scanner;

class Main {

    private static final int        MAX_WEEK = 18;
    private static final String     EOF = "42";
    private static final String     TITLE = "Week ";
    private static final int        ARG_COUNT = 5;

    public static void  printIllustration(long marks) {
    
        int     mark = 0;
        int     weekNo = 0;

        while (marks != 0) {
            weekNo++;
            mark = (int) marks % 10;
            marks /= 10;
            
            System.out.print("Week " + weekNo + " ");
            for (int i = 0; i < mark; i++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static int   getMinMark(Scanner marks) {

        int     minMark = 9;
        int     currentMark = 0;

        for (int i = 0; i < ARG_COUNT; i++) {

            currentMark = marks.nextInt();
            if (currentMark >= 10 || currentMark < 1) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            if (minMark > currentMark) {
                minMark = currentMark;
            }
        }
        return (minMark);
    }

    public static void  main(String[] args) {

        Scanner     scan = new Scanner(System.in);
        String      inputLine = "";
        int         weekCount = 0;
        long        minMarksRow = 0;
        int         multiplicator = 1;

        while (!((inputLine = scan.nextLine()).equals(EOF)) && weekCount <= MAX_WEEK) {
            if (inputLine.equals(TITLE + (weekCount + 1))) {

                String      marks = scan.nextLine();
                Scanner     scanM = new Scanner(marks);

                minMarksRow += getMinMark(scanM) * multiplicator;
                multiplicator *= 10;
            }
            else {
                System.err.println("IllegalArgument--");
                System.exit(-1);
            }
            weekCount += 1;
        }
        printIllustration(minMarksRow);
    }
}