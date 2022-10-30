import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<String>   _blockingQueue = null;

    public              Consumer(BlockingQueue<String> queue) {

        _blockingQueue = queue;
    }

    @Override
    public void     run() {
        while(true) {
            try {
                String element = _blockingQueue.take();
                String text = Thread.currentThread().getName() + " consumed " + element;
                System.out.println(text);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
