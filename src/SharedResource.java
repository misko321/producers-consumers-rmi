

public class SharedResource {
  private Semaphore cs = new FairSemaphore();//"cs");
  private Semaphore full = new FairSemaphore(0);//"full");
  private Semaphore empty = new UnfairSemaphore(2);//"empty");

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
    cs(count);
    cs.V();
    full.V(count); //TODO P&V -> longer names?
  }

  public void remove(int count) {
    full.P(count);
    cs.P();
    cs(-count);
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

  // public void log() {
  //   System.out.println("cs wakes: " + cs.wakes);
  //   System.out.println("empty wakes: " + empty.wakes);
  //   System.out.println("full wakes: " + full.wakes);
  //
  //   System.out.println("all wakes: " + (cs.wakes + empty.wakes + full.wakes));
  // }
}
