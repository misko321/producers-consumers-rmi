import java.lang.Thread;

public class Test {
  public static void main(String[] args) {
    System.out.println("hello world Test");

    SharedResource res = new SharedResource();

    Thread thread1 = new Thread(new MyThread(res));
    Thread thread2 = new Thread(new MyThread(res));

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(res.i);
  }
}
