import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<String>   _blockingQueue = null;

    public              Producer(BlockingQueue<String> queue) {

        _blockingQueue = queue;
    }

    @Override
    public void     run() {
        while(true) {
            long    timeMillis = System.currentTimeMillis();
            try {
                _blockingQueue.put("" + timeMillis);
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
            sleep(1000);
        }
    }

    private void    sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
