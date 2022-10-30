public class Egg extends Thread {

    private final int   _count;

    public              Egg(int cnt) {
        _count = cnt;
    }

    @Override
    public void         run() {
        for (int i = 0; i < _count; i++) {
            synchronized(Program.obj) {
                try {
                    System.out.println("Egg");
                    Program.obj.notify();
                    Program.obj.wait(1);
                } catch (InterruptedException interruptedException) {
                System.exit(-1);
                }
            }
        }
    }
}
