import java.lang.Runnable;

public class MyThread implements Runnable {
  public SharedResource res;
  public int action;

  public MyThread(SharedResource res, int action) {
    this.res = res;
    this.action = action;
  }

  public void run() {
    int rounds = 20;

    switch (action) {
      case 0: {
        for (int i = 0; i < rounds; ++i)
          res.add(1);
        break;
      }
      case 1: {
        for (int i = 0; i < rounds; ++i)
          res.remove(1);
        break;
      }
      case 2: {
        for (int i = 0; i < rounds / 2; ++i)
          res.remove(2);
        break;
      }
    }

    // System.out.println(Thread.currentThread().getId() + " finished");
  }
}
