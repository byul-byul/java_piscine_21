import java.util.Scanner;

class Main {

    private static final int        MAX_WEEK = 18;
    private static final String     EOF = "42";
    private static final String     TITLE = "Week ";
    private static final int        ARG_COUNT = 5;


    public static void  main(String[] args) {

        Scanner     scan = new Scanner(System.in);
        Scanner     scanForSkip = new Scanner(System.in);

        String      inputLine = "";
        String      skipLine = "";

        int         weekCount = 0;
        //boolean     isWeekLine = true;
        long        minMarksRow = 0;

        //System.out.println(weekCount);
        while (!((inputLine = scan.nextLine()).equals(EOF)) && weekCount <= MAX_WEEK) {
            System.out.println("--I HAVE READED THIS ONE--");   // debajim
            System.out.println(inputLine);                      // debajim
            System.out.println(weekCount);                      // debajim
            System.out.println(inputLine.equals(EOF));                      // debajim
            System.out.println(!(inputLine.equals(EOF)) && weekCount <= MAX_WEEK);                      // debajim
            System.out.println("--THE END--");                  // debajim
            //if (isWeekLine && (inputLine.equals(TITLE + (weekCount + 1)))) {
            if (inputLine.equals(TITLE + (weekCount + 1))) {

                //System.out.println(inputLine + " " + isWeekLine);
                //inputLine = scan.nextLine();
                int mul = 1;
                int mark = 0;
                for (int i = 0; i < ARG_COUNT; i++) {
                    mark = scan.nextInt();
                    if (mark >= 10 || mark < 1) {
                        System.out.println("IllegalArgument");
                        System.exit(-1);
                    }
                    minMarksRow += mark * mul;
                    mul *= 10;
                }
                System.out.println(minMarksRow);
                //skipLine = scanForSkip.nextLine();                  // debajim
                //System.out.println("--NOW ILL SHOW YOU SKIPLINE--");   // debajim
                //System.out.println(skipLine);                      // debajim
                //System.out.println("--END OF SKIPLINE--");                  // debajim
            }
            else {
                System.out.println("IllegalArgument--");
                System.exit(-1);
            }
            weekCount += 1;
            System.out.println(weekCount);
        }
        System.out.println(weekCount);
    }
}