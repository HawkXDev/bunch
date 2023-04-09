package collections;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueEx {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();

        // add items to the queue with different delay times
        delayQueue.put(new DelayedElement("element1", 5000));
        delayQueue.put(new DelayedElement("element2", 10000));
        delayQueue.put(new DelayedElement("element3", 15000));

        // wait for the first element to become available for extraction
        DelayedElement element = delayQueue.take();
        System.out.println(element.getName() + " is ready!");

        // wait for the second one
        element = delayQueue.take();
        System.out.println(element.getName() + " is ready!");

        // wait for the third
        element = delayQueue.take();
        System.out.println(element.getName() + " is ready!");
    }
}

class DelayedElement implements Delayed {

    private final String name;
    private final long delay;

    public DelayedElement(String name, long delay) {
        this.name = name;
        this.delay = System.currentTimeMillis() + delay;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remainingDelay = delay - System.currentTimeMillis();
        return unit.convert(remainingDelay, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        long diff = this.delay - ((DelayedElement) other).delay;
        return Long.compare(diff, 0);
    }

}
