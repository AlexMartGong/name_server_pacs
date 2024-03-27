package org.ax.jdbc.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {

        try {
            var objectActivity = new ActivityInterface();
            Registry registry = LocateRegistry.createRegistry(5002);
            registry.rebind("objServer", objectActivity);
            System.out.println("Object public");
            System.out.println(objectActivity);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
}
