package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProducersConsumersInterface extends Remote {
    Object add(int count) throws RemoteException;
    Object remove(int count) throws RemoteException;
}
