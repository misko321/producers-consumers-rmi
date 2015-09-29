package local;

import common.*;
import java.lang.Runnable;

public class ExampleThread implements Runnable {
  public SharedResource res;
  public int action;

  public ExampleThread(SharedResource res, int action) {
    this.res = res;
    this.action = action;
  }

  public void run() {
    int rounds = 40;

    //only for test
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
          res.add(2);
        break;
      }
      case 3: {
        for (int i = 0; i < rounds / 4; ++i)
          res.remove(4);
        break;
      }
    }
  }
}
