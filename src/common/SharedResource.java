package common;

import semaphore.*;

public class SharedResource {
  private Semaphore cs, full, empty;

  private int items = 0;

  public SharedResource(int bufferSize) {
    cs = new FairSemaphore();
    full = new FairSemaphore(0);
    empty = new UnfairSemaphore(bufferSize);
  }

  public void add(int count) {
    empty.acquire(count);
    cs.acquire();
    cs(count);
    cs.release();
    full.release(count);
  }

  public void remove(int count) {
    full.acquire(count);
    cs.acquire();
    cs(-count);
    cs.release();
    empty.release(count);
  }

  private void cs(int count) {
    System.out.print("(TID=" + Thread.currentThread().getId() + "): " + items);
    items += count;
    System.out.println(" -> " + items + " (" + (count > 0 ? "ADD " : "REMOVE ") + Math.abs(count) +")");
  }

  public int getItems() {
    return items;
  }
}
