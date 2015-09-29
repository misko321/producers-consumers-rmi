package local;

import common.*;
import java.lang.Runnable;

public class ExampleThread implements Runnable {
  private SharedResource res;
  private int count;
  private int rounds;


  public ExampleThread(SharedResource res, int count, int rounds) {
    this.res = res;
    this.count = count;
    this.rounds = rounds;
  }

  public void run() {
    for (int i = 0; i < rounds; ++i) {
      if (count > 0) //producer
        res.add(count);
      else //consumer
        res.remove(-count);
    }
  }
}
