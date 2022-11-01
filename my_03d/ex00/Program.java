public class Program {

    private static void     _printErrAndExit() {
        System.out.println("Wrong argument!\nExample: java Program --count=21");
        System.exit(-1);
    }
    
    public static void      main(String[] args) {
        if (args.length != 1) {
            _printErrAndExit();
        }
        String[] splittedArgs = args[0].split("=");
        if (splittedArgs.length == 2 && splittedArgs[0].equals("--count")) {
            int     printCount = 0;
            try {
                printCount = Integer.parseInt(splittedArgs[1]);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
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
            for (int i = 0; i < printCount; i++) {
                System.out.println("Human");
            }
        } else {
            _printErrAndExit();
        }
    }
}