import java.util.concurrent.ThreadLocalRandom;

public class Program {

    public static int[]     intArray;

    private static void     _printErrAndExit() {
        System.out.println("Wrong argument!\nExample: java Program --arraySize=13 --threadsCount=3");
        System.exit(-1);
    }

    public static void      main(String[] args) {

        int     mainSum = 0;
        int     sumByThreads = 0;

        if (args.length != 2) {
            _printErrAndExit();
        }

        String[]    splittedFirstArg = args[0].split("=");
        String[]    splittedSecondArg = args[1].split("=");

        if (splittedFirstArg.length != 2 || 
            !splittedFirstArg[0].equals("--arraySize") ||
            splittedSecondArg.length != 2 || 
            !splittedSecondArg[0].equals("--threadsCount")) {
            _printErrAndExit();
        }
        int     arraySize = 0;
        int     threadsCount = 0;
        try {
            arraySize = Integer.parseInt(splittedFirstArg[1]);
            threadsCount = Integer.parseInt(splittedSecondArg[1]);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        if (arraySize < threadsCount || arraySize < 1 || threadsCount < 1) {
            _printErrAndExit();
        }
        intArray = new int[arraySize];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
            mainSum += intArray[i];
        }
        System.out.println("Sum: " + mainSum);

        int         start = 0;
        int         fin = -1;
        int         extraCount = arraySize % threadsCount;
        int         cnt1 = arraySize / threadsCount;
        int         cnt2 = cnt1;
        SumThread[] sumThreadList = new SumThread[threadsCount];

        if (extraCount + cnt1 == threadsCount) {
            cnt1 += 1;
            cnt2 = arraySize - cnt1 * (threadsCount - 1);
        } else {
            cnt2 += extraCount;
        }
        for (int i = 0; i < threadsCount; i++) {
            start = fin + 1;
            if (i == threadsCount - 1) {
                fin = start + cnt2 - 1;
            }
            else {
                fin = start + cnt1 - 1;
            }
            sumThreadList[i] = new SumThread(i, start, fin);
            sumThreadList[i].start();
            sumByThreads += sumThreadList[i].calculateSum(start, fin);
        }
        try {
            for (int i = 0; i < threadsCount; i++) {
                sumThreadList[i].join();
            }
        } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
        }
        System.out.println("Sum by threads: " + sumByThreads);
    }
}
