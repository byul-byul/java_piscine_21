public class TestThread {

    static Object                       obj = new Object();

    public static void                  main(String[] args) {

        int         printCount = 55;
        ThreadA     aa = new ThreadA(printCount);
        ThreadB     bb = new ThreadB(printCount);

        aa.start();
        bb.start();
        try {
        aa.join();
        bb.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}