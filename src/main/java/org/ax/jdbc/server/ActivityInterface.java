package org.ax.jdbc.server;

import org.ax.jdbc.connection.ConnectionDatabase;
import org.ax.jdbc.middleware.IMethodsShred;
import org.ax.jdbc.middleware.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.ax.jdbc.server.PeopleRepository.personList;

public class ActivityInterface extends UnicastRemoteObject implements IMethodsShred {

    private Connection getConnection() throws SQLException {
        return ConnectionDatabase.getInstance();
    }

    protected ActivityInterface() throws RemoteException {
    }

    @Override
    public boolean addPerson(Person p) throws RemoteException {
        String sql = "INSERT INTO people(name, address, phone_number) VALUES(?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getAddress());
            stmt.setString(3, p.getPhoneNumber());

            stmt.executeUpdate();
            System.out.println("Add person: " + true);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletePerson(Person p) throws RemoteException {

        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM people WHERE id_person=?")) {

            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            System.out.println("Delete person: " + true);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> consultPerson(String filter) throws RemoteException {
        personList.clear();
        String sql = "SELECT * FROM people WHERE (name LIKE ? OR address LIKE ? OR phone_number LIKE ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + filter + "%");
            stmt.setString(2, "%" + filter + "%");
            stmt.setString(3, "%" + filter + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Person p = new Person();
                    p.setId(rs.getInt("id_person"));
                    p.setName(rs.getString("name"));
                    p.setAddress(rs.getString("address"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    personList.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Filter");
        return personList;
    }



    @Override
    public boolean updatePerson(Person p) throws RemoteException {
        String sql = "UPDATE people SET name=?, address=?, phone_number=? WHERE id_person=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getAddress());
            stmt.setString(3, p.getPhoneNumber());
            stmt.setInt(4, p.getId());

            stmt.executeUpdate();
            System.out.println("Modify: " + true);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
