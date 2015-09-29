package rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("The program takes 2 arguments:\n * [address:port] of the RMI server\n" +
      " * [number] of units to produce in a single round");
      System.exit(-1);
    }

    System.out.println("Starting Producer client...");
    //  if (System.getSecurityManager() == null)
    //      System.setSecurityManager(new SecurityManager());
    ProducersConsumersInterface remoteInterface = null;
    Registry registry;

    try {
      try {
        remoteInterface = (ProducersConsumersInterface) Naming.lookup("//" + args[0] + "/ProducersConsumersServer");
      } catch (MalformedURLException e) {
        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
      }

      int units = Integer.parseInt(args[1]);
      int round = 1;
      while (true) {
        remoteInterface.add(units);
        System.out.println(round + ": Pushed " + units + " unit(s) to the server");
        ++round;
      }
    } catch (RemoteException | NotBoundException e) {
      System.out.println("!Connection with server was broken. Either the server is down or " +
        "there is a problem with reaching it.");
    }
  }
}
