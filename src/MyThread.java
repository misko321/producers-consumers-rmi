import java.lang.Runnable;

public class MyThread implements Runnable {
  public SharedResource res;
  public int action;

  public MyThread(SharedResource res, int action) {
    this.res = res;
    this.action = action;
  }

  public void run() {
    for (int i = 0; i < 10; ++i) {
      if (action == 0)
        res.add(1);
      else if (action == 1)
        res.remove(1);
      else if (action == 2)
        res.remove(2);
      else
        res.add(2);
      // res.unlock();
    }

    System.out.println(Thread.currentThread().getId() + " finished");
  }
}
