import java.util.Deque;
import java.util.ArrayDeque;

public class Semaphore {
  private int value;
  private Deque<Long> locks = new ArrayDeque<>();
  // private Object lock = new Object();

  // private class Lock {
  //   // public Object lock;
  //   public int value;
  //
  //   public Lock(int value) {
  //     // this.lock = new Object();
  //     this.value = value;
  //   }
  // }

  public Semaphore() {
    this.value = 1;
  }

  public Semaphore(int value) {
    this.value = value;
  }

  public void P() {
    P(1);
  }

  public void V() {
    V(1);
  }

  public synchronized void P(int units) {
    synchronized (this) {
      Long lock = Thread.currentThread().getId();
      locks.addLast(lock);

      while (units > value || (!locks.isEmpty() && locks.getFirst() != lock)) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
        }
      }

      value -= units;
      if (!locks.isEmpty())
        locks.removeFirst(); //remove this thread's object (== remove(obj)) TODO ?
      notifyAll();
    }
  }

  public synchronized void V(int units) {

    synchronized (this) {
      value += units;
      notifyAll();
    }
}

  // private synchronized void addLast(Lock lock) {
  //   locks.addLast(lock);
  // }
  // private synchronized Lock getFirst() {
  //   return locks.getFirst();
  // }
  // private synchronized Lock removeFirst() {
  //   return locks.removeFirst();
  // }



	public int getValue() {
		return value;
	}
}
