public class Program {

    static Object           obj = new Object();

    public static void      printMsg(String msg) {
        System.out.println(msg);
    }

    private static void     _printErrAndExit() {
        System.out.println("Wrong argument!\nExample: java Program --threadsCount=2");
        System.exit(-1);
    }
    
    public static void      main(String[] args) {

        if (args.length != 1) {
            _printErrAndExit();
        }

        String [] splittedArgs = args[0].split("=");

        if (splittedArgs.length == 2 && splittedArgs[0].equals("--threadsCount")) {
            int     printCount = Integer.parseInt(splittedArgs[1]);
            if (printCount < 0) {
                _printErrAndExit();
            }
            Hen     egg = new Hen(printCount);
            Egg     hen = new Egg(printCount);

            egg.start();
            hen.start();
            try {
                egg.join();
                hen.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        else {
            _printErrAndExit();
        }
    }
}