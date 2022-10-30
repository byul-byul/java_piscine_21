import java.util.concurrent.ThreadLocalRandom;

public class Program {

    public static int[]     intArray;

    private static void     _printErrAndExit() {
        System.out.println("Wrong argument!\nExample: java Program --arraySize=13 --threadsCount=3");
        System.exit(-1);
    }
    
    public static void      main(String[] args) {

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

        int         arraySize = Integer.parseInt(splittedFirstArg[1]);
        int         threadsCount = Integer.parseInt(splittedSecondArg[1]);

        if (arraySize < threadsCount || arraySize < 1 || threadsCount < 1) {
            _printErrAndExit();
        }

        intArray = new int[arraySize];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = ThreadLocalRandom.current().nextInt(-1000, 1000 + 1);
        }
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

        int             start = 0;
        int             fin = Math.round(arraySize / threadsCount); //например 17 / 3 = 5
        int             step = fin;
        SumThread[]     sumThreadList = new SumThread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {

            sumThreadList[i] = new SumThread(i, start, fin); // i=0; start=0; fin=5
            sumThreadList[i].start();
            start = fin; // start = 5
            fin += step; // fin = 10
            if (fin > arraySize) {
                fin = arraySize;
            }
        }
        //for (int i = 0; i < threadsCount; i++) {
            //sumThreadList[i] = new SumThread(i);
            //sumThreadList[i].start();
        //}
        try {
            for (int i = 0; i < threadsCount; i++) {
                sumThreadList[i].join();
            }
        } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
        }
    }
}
