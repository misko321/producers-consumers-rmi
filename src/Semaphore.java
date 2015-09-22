import java.util.Deque;
import java.util.ArrayDeque;

public class Semaphore {
  private int value;
  private Deque<Object> locks = new ArrayDeque<>();
  private Object lock = new Object();

  // private class Lock {
  //   public Object lock;
  //   public int value;
  //
  //   public Lock(int value) {
  //     this.lock = new Object();
  //     this.value = value;
  //   }
  // }

  public Semaphore(int value) {
    this.value = value;
  }

  public void P() {
    P(1);
  }

  public void V() {
    V(1);
  }

  public void P(int units) {
    synchronized (lock) {
    try {
      while (units > value) {
        // Object obj = new Object();
        // locks.addLast(obj);
        lock.wait();
      }
      // locks.removeFirst(); //remove this thread's object (== remove(obj)) TODO ?
      value -= units;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  }

  public void V(int units) {
    synchronized (lock) {
    value += units;
    // if (!locks.isEmpty()) {
      // Object obj = locks.removeFirst();
      lock.notify();
    // }
    //TODO nofity only when enough units
  }
  }





	public int getValue() {
		return value;
	}

  public void setValue(int value) {
		this.value = value;
	}
}
