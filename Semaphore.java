

public class Semaphore {
  private int value;

  public Semaphore(int value) {
    this.value = value;
  }

  public void P() {
    P(1);
  }

  public void V() {
    V(1);
  }

  public synchronized void P(int units) {
    try {
      while (units > value)
        wait();
      value -= units;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public synchronized void V(int units) {
    value += units;
    notify();
  }





	public int getValue() {
		return value;
	}

  public void setValue(int value) {
		this.value = value;
	}
}
