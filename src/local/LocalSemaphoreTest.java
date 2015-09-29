package local;

import java.lang.Thread;
import common.*;

public class LocalSemaphoreTest {
  public static void main(String[] args) {
    System.out.println("Starting local test of semaphores...\n");

    SharedResource res = new SharedResource(4);

    Thread thread1 = new Thread(new ExampleThread(res, 0));
    Thread thread2 = new Thread(new ExampleThread(res, 1));
    Thread thread3 = new Thread(new ExampleThread(res, 2));
    Thread thread4 = new Thread(new ExampleThread(res, 3));

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    try {
      thread1.join();
      thread2.join();
      thread3.join();
      thread4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("\nNumber of the elements at the end: " + res.getItems() + " (should be 0)");
  }
}
