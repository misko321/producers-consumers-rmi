package semaphore;

public abstract class Semaphore {
  protected int units;

  public Semaphore() {
    this.units = 1;
  }

  public Semaphore(int units) {
    this.units = units;
  }

  public void acquire() {
    acquire(1);
  }

  public void release() {
    release(1);
  }

  public abstract void acquire(int units);
  public abstract void release(int units);

  public int getUnits() {
    return units;
  }
}
