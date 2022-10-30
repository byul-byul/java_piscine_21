public class Egg extends Thread {

    private final int   _count;

    public              Egg(int cnt) {
        _count = cnt;
    }

    public void         run() {
        for (int i = 0; i < _count; i++) {
            System.out.println("Egg");
        }
    }
}
