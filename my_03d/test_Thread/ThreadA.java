public class ThreadA extends Thread {

    private int     _count;

    public          ThreadA(int count) {
        _count = count;
    }

    public void     run() {
        for (int i = 0; i < _count; i++) {
            synchronized(TestThread.obj) {
                try {
                    System.out.println("AAA");
                    TestThread.obj.notify();
                    TestThread.obj.wait(1);
                } catch (InterruptedException interruptedException) {
                System.exit(-1);
                }
            }
        }
    }
}