package rmi; // 1. Added package declaration to match your interface

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;


public class RMIServer extends UnicastRemoteObject implements RemoteInterface {
    
    // 2. Fixed constructor to correctly call the parent class constructor
    public RMIServer() throws RemoteException {
        super(); // Initializes the UnicastRemoteObject to listen for remote calls
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello world!";
    }

    public static void main(String[] args) {
        try {
            RMIServer rmiserver = new RMIServer();
            
            // Creates the RMI registry locally on port 5099
            Registry registry = LocateRegistry.createRegistry(5099);
            
            // rebind is safer than bind because it overwrites old references if re-run
            registry.rebind("HelloService", rmiserver);
            
            System.out.println("Server is waiting for remote call...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
            e.printStackTrace();
        }
    }
}