public class SumThread extends Thread {

    private int     _index;
    private int     _start;
    private int     _fin;

    private int     _getSum(int s, int f) {

        int         sum = 0;
        for(int i = s; i < f; i++) {
            sum += Program.intArray[i];
        }
        return (sum);
    }

    public          SumThread(int i, int s, int f) {
        _index = i;
        _start = s;
        _fin = f;
    }

    @Override
    public void     run() {
        System.out.println("Thread " + (_index + 1) + ": start=" + _start + "; fin=" + _fin + " sum=" + _getSum(_start, _fin));
    }
}
