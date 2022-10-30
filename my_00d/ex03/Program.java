import java.util.Scanner;

public class Program {
    private static final long       MAX_WEEK = 18;
    private static final String     EOF = "42";
    private static final String     TITLE = "Week ";
    private static final long       ARG_COUNT = 5;

    public static void  printIllustration(long marks) {
        long    mark = 0;
        long    weekNo = 0;

        while (marks != 0) {
            weekNo++;
            mark = (long) marks % 10;
            marks /= 10;
            
            System.out.print("Week " + weekNo + " ");
            for (int i = 0; i < mark; i++) {
                System.out.print("=");
            }
            if (mark > 0) {
                System.out.println(">");
            }
        }
    }

    public static long   getMinMark(Scanner marks) {

        long    minMark = 9;
        long    currentMark = 0;

        for (int i = 0; i < ARG_COUNT; i++) {

            currentMark = marks.nextLong();
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
        long        weekCount = 0;
        long        minMarksRow = 0;
        long        multiplicator = 1;

        while (!((inputLine = scan.nextLine()).equals(EOF)) && weekCount <= MAX_WEEK) {
            if (inputLine.equals(TITLE + (weekCount + 1))) {

                String      marks = scan.nextLine();
                Scanner     scanM = new Scanner(marks);

                minMarksRow += getMinMark(scanM) * multiplicator;
                multiplicator *= 10;
            }
            else {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            weekCount += 1;
        }
        printIllustration(minMarksRow);
    }
}
