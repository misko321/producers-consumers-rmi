import java.util.Deque;
import java.util.ArrayDeque;

public class Semaphore {
  private int value;
  private Deque<Lock> locks = new ArrayDeque<>();
  // private Object lock = new Object();
  public int wakes;

  private class Lock {
    // public Object lock;
    public int value;

    public Lock() {
      // this.lock = new Object();
      // this.value = value;
    }
  }

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

  public void P(int units) {
    Lock lock;

    synchronized (this) {
      lock = new Lock();
      locks.addLast(lock);
    }

    synchronized(lock) {
      while (units > value || (!locks.isEmpty() && locks.getFirst() != lock)) {
        try {
          lock.wait();
          ++wakes;
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
        }
      }
    }

    synchronized (this) {
      value -= units;
      // if (!locks.isEmpty())
        locks.remove(lock); //remove this thread's object (== remove(obj)) TODO ?
        lock = null;
    }

    if (!locks.isEmpty())
      lock = locks.getFirst();

    if (lock != null) {
      synchronized (lock) {
        lock.notify();
      }
    }
  }

  public void V(int units) {
    Lock lock;

    synchronized (this) {
      value += units;
      if (!locks.isEmpty())
        lock = locks.getFirst();
      else
        return;
    }

    synchronized (lock) {
      lock.notify();
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
