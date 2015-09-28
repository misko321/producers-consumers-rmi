
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumer extends UnicastRemoteObject
        implements ProducerConsumerInterface {

    Registry registry;
    SharedResource res;

    public ProducerConsumer() throws RemoteException {
        res = new SharedResource();
        try {
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ProducerConsumerServer", this);
        } catch (RemoteException e) {
            throw e;
        }
    }

    public static void main(String args[]) {
        System.out.println("Starting ProducerConsumerServer server...");
      //  if (System.getSecurityManager() == null)
      //      System.setSecurityManager(new SecurityManager());

        try {
            ProducerConsumer s = new ProducerConsumer();
        } catch (RemoteException e) {
            System.exit(1);
        }
    }

    @Override
    public Object add(int count) throws RemoteException {
        res.add(count);
        // System.out.println("ADD " + count + " unit(s)");
        return null;
    }

    @Override
    public Object remove(int count) throws RemoteException {
        res.remove(count);
        // System.out.println("REMOVE " + count + " unit(s)");
        return null;
    }

}
