
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author inf106640
 */
public interface ProducerConsumerInterface extends Remote {
    Object add(int count) throws RemoteException;
    Object remove(int count) throws RemoteException;
}
