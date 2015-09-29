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

  // public void lock() {
  //   cs.acquire();
  // }
  //
  // public void unlock() {
  //   semaphore.release();
  // }

  public void add(int count) {
    empty.acquire(count);
    cs.acquire();
    cs(count);
    cs.release();
    full.release(count); //TODO acquire&release -> longer names?
  }

  public void remove(int count) {
    full.acquire(count);
    cs.acquire();
    cs(-count);
    cs.release();
    empty.release(count);
  }

  private void cs(int count) {
    // try {
    //   Thread.sleep(100);
    // } catch (InterruptedException e) { }
    // System.out.println("now " + in);
    System.out.print("(TID=" + Thread.currentThread().getId() + "): " + items);
    items += count;
    System.out.println(" -> " + items + " (" + (count > 0 ? "ADD " : "REMOVE ") + Math.abs(count) +")");

    // try {
    //   Thread.sleep(500);
    // } catch (InterruptedException e) { }
  }

  public int getItems() {
    return items;
  }

  // public void log() {
  //   System.out.println("cs wakes: " + cs.wakes);
  //   System.out.println("empty wakes: " + empty.wakes);
  //   System.out.println("full wakes: " + full.wakes);
  //
  //   System.out.println("all wakes: " + (cs.wakes + empty.wakes + full.wakes));
  // }
}
