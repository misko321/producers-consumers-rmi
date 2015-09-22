

public class SharedResource {
  Semaphore semaphore = new Semaphore(1);
  public int i = 0;

  public void lock() {
    semaphore.P();
  }

  public void unlock() {
    semaphore.V();
  }

  public void increment() {
    ++i;
    // System.out.println(i);
  }
}
