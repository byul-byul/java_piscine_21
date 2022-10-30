import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern {

    public static void  main(String[] args) {

        BlockingQueue<String>   blockingQueue = new ArrayBlockingQueue<>(3);

        Producer                producer    = new Producer(blockingQueue);
        Consumer                consumer_1  = new Consumer(blockingQueue);
        Consumer                consumer_2  = new Consumer(blockingQueue);

        Thread                  producerThread      = new Thread(producer);
        Thread                  consumerThread_1    = new Thread(consumer_1);
        Thread                  consumerThread_2    = new Thread(consumer_1);

        producerThread.start();
        consumerThread_1.start();
        consumerThread_2.start();

    }
}