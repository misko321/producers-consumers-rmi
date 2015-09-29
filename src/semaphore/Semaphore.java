package semaphore;

public abstract class Semaphore {
  protected int units;

  public Semaphore() {
    this.units = 1;
  }

  public Semaphore(int units) {
    this.units = units;
  }

  public void P() {
    P(1);
  }

  public void V() {
    V(1);
  }

  public abstract void P(int units);
  public abstract void V(int units);

  public int getUnits() {
    return units;
  }
}
