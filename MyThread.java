import java.lang.Runnable;

public class MyThread implements Runnable {
  public SharedResource res;

  public MyThread(SharedResource res) {
    this.res = res;
  }

  public void run() {
    for (int i = 0; i < 100000; ++i) {
      res.lock();
      res.increment();
      res.unlock();
    }
  }
}
