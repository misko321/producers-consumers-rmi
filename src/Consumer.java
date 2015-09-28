/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer {

  public static void main(String[] args) {
    System.out.println("Starting Consumer client...");
    //  if (System.getSecurityManager() == null)
    //      System.setSecurityManager(new SecurityManager());
    ProducerConsumerInterface remoteInterface = null;
    Registry registry;
    //String serverAddr = args[0];
    String serverAddr = "localhost";
    try {
      //registry = LocateRegistry.getRegistry(serverAddr);
      try {
        remoteInterface = (ProducerConsumerInterface) Naming.lookup("//localhost:1099/ProducerConsumerServer");
      } catch (MalformedURLException ex) {
        // Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
      }

      for (int i = 0; i < 5; ++i) {
        remoteInterface.remove(2);
        System.out.println("Removed 2 units from the server");
      }

      } catch (RemoteException | NotBoundException e) { }
  }
}
