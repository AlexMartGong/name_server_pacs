package org.ax.jdbc.server;

import org.ax.jdbc.middleware.IMethodsShred;
import org.ax.jdbc.middleware.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ActivityInterface extends UnicastRemoteObject implements IMethodsShred {

    protected ActivityInterface() throws RemoteException {
    }

    @Override
    public boolean addPerson(Person p) throws RemoteException {
        return false;
    }

    @Override
    public boolean deletePerson(Person p) throws RemoteException {
        return false;
    }

    @Override
    public List<Person> consultPerson(String filter) throws RemoteException {
        return null;
    }

    @Override
    public boolean updatePerson(Person p) throws RemoteException {
        return false;
    }
}
