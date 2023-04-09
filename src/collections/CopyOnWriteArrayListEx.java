package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListEx {
    public static void main(String[] args) throws InterruptedException {
//        ArrayList<String> list = new ArrayList<>(); - a ConcurrentModificationException will be thrown
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Andrew");
        list.add("Oleg");
        list.add("Sergey");
        list.add("Ivan");
        list.add("Igor");

        System.out.println(list);

        Runnable runnable1 = () -> {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove(4);
            list.add("Elena");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(list);
    }
}
/* Output:
[Andrew, Oleg, Sergey, Ivan, Igor]
Andrew
Oleg
Sergey
Ivan
Igor
[Andrew, Oleg, Sergey, Ivan, Elena]
*/
