package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.Queue;

import common.*;

public class ProducersConsumers extends UnicastRemoteObject
        implements ProducersConsumersInterface {

  Registry registry;
  SharedResource res;

  public ProducersConsumers(int port, int bufferSize) throws RemoteException {
    res = new SharedResource(bufferSize);
    try {
        registry = LocateRegistry.createRegistry(port);
        registry.rebind("ProducersConsumersServer", this);
    } catch (RemoteException e) {
        throw e;
    }
  }

  public static void main(String args[]) {
    if (args.length != 2) {
      System.out.println("The program takes 2 arguments:\n * [port] that the server should run at\n" +
        " * [size] of the buffer");
      System.exit(-1);
    }

    int port = Integer.parseInt(args[0]);
    int bufferSize = Integer.parseInt(args[1]);
    System.out.println("Starting ProducersConsumersServer server at port " + port +
    " with " + bufferSize + " unit(s) buffer...");
    //  if (System.getSecurityManager() == null)
    //      System.setSecurityManager(new SecurityManager());
    try {
        ProducersConsumers s = new ProducersConsumers(port, bufferSize);
    } catch (RemoteException e) {
        System.exit(1);
    }
  }

  @Override
  public Object add(int count) throws RemoteException {
    //add some artificial delay
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) { }

    res.add(count);
    return null;
  }

  @Override
  public Object remove(int count) throws RemoteException {
    res.remove(count);
    return null;
  }
}
