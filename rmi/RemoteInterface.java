package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException; // Added this import to prevent a secondary error

// Remote interface
public interface RemoteInterface extends Remote {
    
    String sayHello() throws RemoteException;
}