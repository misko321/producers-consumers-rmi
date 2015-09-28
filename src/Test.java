import java.lang.Thread;

public class Test {
  public static void main(String[] args) {
    System.out.println("hello world Test");

    SharedResource res = new SharedResource();

    Thread thread1 = new Thread(new MyThread(res, 0));
    Thread thread2 = new Thread(new MyThread(res, 0));
    Thread thread3 = new Thread(new MyThread(res, 1));
    Thread thread4 = new Thread(new MyThread(res, 2));

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

    System.out.println("\nAt the end: " + res.i);
  }
}
