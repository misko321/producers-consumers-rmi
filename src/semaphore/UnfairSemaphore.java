package semaphore;

import java.util.Deque;
import java.util.ArrayDeque;

public class UnfairSemaphore extends Semaphore {

  public UnfairSemaphore(){
  }

  public UnfairSemaphore(int units) {
    super(units);
  }

  @Override
  public synchronized void acquire(int units) {
    while (units > this.units) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }

    this.units -= units;
    notifyAll();
  }

  @Override
  public synchronized void release(int units) {
    this.units += units;
    notifyAll();
  }
}
