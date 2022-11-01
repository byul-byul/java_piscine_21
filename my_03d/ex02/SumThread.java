public class SumThread extends Thread {

    private int     _index;
    private int     _start;
    private int     _fin;
    private int     _sum = 0;

    public int      calculateSum(int s, int f) {
        for(int i = s; i <= f; i++) {
            _sum += Program.intArray[i];
        }
        return (_sum);
    }

    public          SumThread(int i, int s, int f) {
        _index = i;
        _start = s;
        _fin = f;
    }

    @Override
    public void     run() {
        System.out.println("Thread " + (_index + 1)
                            + ": from " + _start
                            + " to " + _fin
                            + " sum is " + calculateSum(_start, _fin));
    }
}
