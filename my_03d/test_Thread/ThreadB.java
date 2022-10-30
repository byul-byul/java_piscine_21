public class ThreadB extends Thread {

    private int     _count;

    public          ThreadB(int count) {
        _count = count;
    }

    public void     run() {
        for (int i = 0; i < _count; i++) {
            synchronized(TestThread.obj) {
                try {
                    TestThread.obj.wait(1);
                    System.out.println("BBB");
                    TestThread.obj.notify();
                } catch (InterruptedException interruptedException) {
                System.exit(-1);
                }
            }
        }
    }
}