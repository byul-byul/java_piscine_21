public class Hen extends Thread {

    private final int   _count;

    public              Hen(int cnt) {
        _count = cnt;
    }

    @Override
    public void         run() {
        for (int i = 0; i < _count; i++) {
            synchronized(Program.obj) {
                try {
                    System.out.println("Hen");
                    Program.obj.notify();
                    Program.obj.wait(1);
                } catch (InterruptedException interruptedException) {
                System.exit(-1);
                }
            }
        }
    }
}


/*public class Hen extends Thread {

    private final int   _count;

    public              Hen(int cnt) {
        _count = cnt;
    }

    public void         run() {

        synchronized(Program.obj) {

            for (int i = 0; i < _count; i++) {
                try {
                    Program.obj.wait();
                } catch (InterruptedException interruptedException) {
                    System.exit(-1);
                }
                Program.printMsg("Hen");
                Program.obj.notify();
            }
        }
    }
}


public class Hen extends Thread {

    private final int   _count;

    public              Hen(int cnt) {
        _count = cnt;
    }

    public void         run() {
        for (int i = 0; i < _count; i++) {
            //System.out.println("Hen");
            /*try {
                this.wait();
            } catch (InterruptedException interruptedException) {
                System.exit(-1);
            }
            Program.printMsg("Hen");
            //this.notify();
        }
    }
}
*/