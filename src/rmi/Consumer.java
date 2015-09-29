package rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("The program takes 2 arguments:\n * [address:port] of the RMI server\n" +
      " * [number] of units to consume in a single round");
      System.exit(-1);
    }

    System.out.println("Starting Consumer client...");
    //  if (System.getSecurityManager() == null)
    //      System.setSecurityManager(new SecurityManager());
    ProducersConsumersInterface remoteInterface = null;
    Registry registry;

    try {
      try {
        remoteInterface = (ProducersConsumersInterface) Naming.lookup("//" + args[0] + "/ProducersConsumersServer");
      } catch (MalformedURLException e) {
        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
      }

      int units = Integer.parseInt(args[1]);
      int round = 1;
      while (true) {
        remoteInterface.remove(units);
        System.out.println(round + ": Removed " + units + " units(s) from the server");
        ++round;
      }

    } catch (RemoteException | NotBoundException e) {
      System.out.println("!Connection with server was broken. Either the server is down or " +
        "there is a problem with connecting it.");
    }
  }
}
