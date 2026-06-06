package rmi; // 1. Added package declaration to match the others

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIClient { 
    
    public static void main(String[] args) {
        try {
            // 2. Connect to the RMI registry running on localhost at port 5099
            Registry registry = LocateRegistry.getRegistry("localhost", 5099);
            
            // 3. Fixed syntax error: Look up the service by its registered name and cast it
            RemoteInterface helloservice = (RemoteInterface) registry.lookup("HelloService");
            
            // 4. Call the remote method
            String string = helloservice.sayHello();
            System.out.println("Server says: " + string);
            
        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
            e.printStackTrace();
        }
    }
}