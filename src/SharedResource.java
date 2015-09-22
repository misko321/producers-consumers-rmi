

public class SharedResource {
  private Semaphore cs = new Semaphore(1);
  private Semaphore full = new Semaphore(0);
  private Semaphore empty = new Semaphore(2);

  public int i = 0;

  // public void lock() {
  //   cs.P();
  // }
  //
  // public void unlock() {
  //   semaphore.V();
  // }

  public void add(int count) {
    empty.P(count);
    cs.P();
    i += count;
    System.out.println(Thread.currentThread().getId() + ": " + i);
    cs.V();
    full.V(count); //TODO P&V -> longer names?
  }

  public void remove(int count) {
    full.P(count);
    cs.P();
    i -= count;
    System.out.println(Thread.currentThread().getId() + ": " + i);
    cs.V();
    empty.V(count);
  }
}
