

public class SharedResource {
  private Semaphore cs = new Semaphore();
  private Semaphore full = new Semaphore(0);
  private Semaphore empty = new Semaphore(2);

  public int i = 0;
  public volatile int in = 0;

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
    ++in;
    cs(count);
    --in;
    cs.V();
    full.V(count); //TODO P&V -> longer names?
  }

  public void remove(int count) {
    full.P(count);
    cs.P();
    ++in;
    cs(-count);
    --in;
    cs.V();
    empty.V(count);
  }

  private void cs(int count) {
    // try {
    //   Thread.sleep(100);
    // } catch (InterruptedException e) { }
    // System.out.println("now " + in);
    System.out.print(Thread.currentThread().getId() + ": " + i);
    i += count;
    System.out.println(" -> " + i + "(" + (count > 0 ? "ADD" : "REMOVE") + ")");

    // try {
    //   Thread.sleep(500);
    // } catch (InterruptedException e) { }
  }
}
