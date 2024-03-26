package org.ax.jdbc.middleware;

import java.rmi.RemoteException;
import java.util.List;

public interface IMethodsShred {

    boolean addPerson(Person p) throws RemoteException;
    boolean deletePerson(Person p) throws RemoteException;
    List<Person> consultPerson(String filter) throws RemoteException;
    boolean updatePerson(Person p) throws RemoteException;

}
