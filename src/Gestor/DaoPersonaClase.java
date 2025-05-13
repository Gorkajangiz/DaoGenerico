/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import static java.util.Optional.empty;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edria
 */
public class DaoPersonaClase implements DaoPersonaInterfaz {

    public DaoPersonaClase() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido el siguiente error: " + ex);
        }
    }

    private Connection con;

    public Connection contactar() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##SCOTT";
        String pass = "tiger";
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    @Override
    public Optional<Persona> findByDNI(String DNI) throws SQLException {
        Optional<Persona> p = null; //deberia retornar una 
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where DNI = ?"); ResultSet rs = ps.executeQuery()) {
            ps.setString(1, DNI);
            while (rs.next()) {
                Long id = rs.getLong("id");
                DNI = rs.getString("DNI");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Integer telefono = rs.getInt("telefono");
                p.orElse(new Persona(DNI, nombre, apellido, telefono, id));
            }
            int r = ps.executeUpdate();
            if (r > 1) {
                System.err.println(r);
            }
            ps.close();
            con.close();
            return p;
        }
    }

    @Override
    public Collection<Persona> findByName(String name) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        this.contactar();
        PreparedStatement ps = con.prepareStatement("select * from Persona where nombre = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Persona p = this.composePersona(rs);
            c.add(p);
        }
        int r = ps.executeUpdate();
        if (r > 1) {
            System.out.println("Hay " + r + " personas con ese nombre");
        }

        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findBySurname(String surname) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where apellido = ?"); ResultSet rs = ps.executeQuery()) {
            this.contactar();
            ps.setString(1, surname);
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
            int r = ps.executeUpdate();
            if (r > 1) {
                System.out.println("Hay: " + r + " personas con ese apellido");
            }
        }
        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findByPhone(Integer phone) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where telefono = ?"); ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, phone);
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
            int r = ps.executeUpdate();
            if (r > 1) {
                System.out.println("Error, no debería haber más de una persona con el mismo numero");
            }
        }
        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findAll() throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
            int r = ps.executeUpdate();
            if (r > 1) {
                System.out.println("Hay " + r + " personas en la lista");
            }
        }
        con.close();
        return c;
    }

    @Override
    public Optional<Persona> findById(Long id) throws SQLException {
        Optional<Persona> p = empty();
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where id = ?"); ResultSet rs = ps.executeQuery()) {
            ps.setLong(1, id);
            while (rs.next()) {
                id = rs.getLong("id");
                String DNI = rs.getString("DNI");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Integer telefono = rs.getInt("telefono");
                p.orElse(new Persona(DNI, nombre, apellido, telefono, id));
            }
            int r = ps.executeUpdate();
            if (r > 1) {
                System.out.println("No debería haber más de una persona con el mismo ID");
            }
        }
        con.close();
        return p;
    }

    @Override
    public void insert(Persona entity) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("insert into Persona values(id_persona.nextval, ?, ?, ?, ?)");) {
            this.contactar();
            ps.setString(1, entity.getDNI());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getApellido());
            ps.setInt(4, entity.getTelefono());
            ps.executeUpdate();
            ps.close();
            con.close();
        }
    }

    @Override
    public void update(Persona entity) throws SQLException {
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("update Persona set telefono = ?, nombre = ?, apellido = ? where dni = ?");) {
            ps.setInt(1, entity.getTelefono());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getDNI());
            ps.setString(4, entity.getDNI());
            int r = ps.executeUpdate();
            ps.close();
            con.close();
        }
    }

    @Override
    public void delete(Persona entity) {
        try {
            PreparedStatement ps;
            String q1 = "delete Persona where DNI = ? and Nombre = ?";
            this.contactar();
            ps = con.prepareStatement(q1);
            ps.setString(1, entity.getDNI());
            ps.setString(2, entity.getNombre());
            int r = ps.executeUpdate();
            if (r > 1) {
                throw new Exception("Hay más de un contacto con ese nombre");
            }
            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonaClase.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement ps;
            String q1 = "delete Persona where id = ?";
            this.contactar();
            ps = con.prepareStatement(q1);
            ps.setLong(1, id);
            int r = ps.executeUpdate();
            if (r > 1) {
                throw new Exception("Hay más de un contacto con ese nombre");
            }
            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonaClase.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Persona composePersona(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String DNI = rs.getString("DNI");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        Integer telefono = rs.getInt("telefono");
        return new Persona(DNI, nombre, apellido, telefono, id);
    }
}
