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
        this.contactar();
        Persona p;
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where DNI = ?")) {
            ps.setString(1, DNI);
            ResultSet rs = ps.executeQuery();
            p = null;
            while (rs.next()) {
                Long id = rs.getLong("id");
                DNI = rs.getString("DNI");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Integer telefono = rs.getInt("telefono");
                p =new Persona(DNI, nombre, apellido, telefono, id);
            }   rs.close();
        }
        con.close();
        return p == null ? Optional.empty(): Optional.of(p);
    }

    @Override
    public Collection<Persona> findByName(String name) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where nombre = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
        }
        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findBySurname(String surname) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where apellido = ?")) {
            ps.setString(1, surname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
        }
        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findByPhone(Integer phone) throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where telefono = ?")) {
            ps.setInt(1, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = this.composePersona(rs);
                c.add(p);
            }
        }
        con.close();
        return c;
    }

    @Override
    public Collection<Persona> findAll() throws SQLException {
        Collection<Persona> c = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("select * from Persona");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Persona p = this.composePersona(rs);
            c.add(p);
        }
        con.close();
        return c;
    }

    @Override
    public Optional<Persona> findById(Long id) throws SQLException {
        Optional<Persona> p = empty(); //cambiar como en por DNI
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("select * from Persona where id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getLong("id");
                String DNI = rs.getString("DNI");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Integer telefono = rs.getInt("telefono");
                p = Optional.of(new Persona(DNI, nombre, apellido, telefono, id));
            }
        }
        con.close();
        return p;
    }

    @Override
    public void insert(Persona entity) throws SQLException {
        this.contactar();
        try (PreparedStatement ps = con.prepareStatement("insert into Persona (nombre, apellido, telefono, dni, id) values(?, ?, ?, ?,id_persona.nextval)")) {
            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getApellido());
            ps.setInt(3, entity.getTelefono());
            ps.setString(4, entity.getDNI());
            ps.executeUpdate();
        }
        con.close();
    }

    @Override
    public int update(Persona entity) throws SQLException {
        this.contactar();
        int rowsAfected;
        try (PreparedStatement ps = con.prepareStatement("update Persona set telefono = ?, nombre = ?, apellido = ? where dni = ?")) {
            ps.setInt(1, entity.getTelefono());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getApellido());
            ps.setString(4, entity.getDNI());
          rowsAfected=   ps.executeUpdate(); 
        }
        con.close();
        return rowsAfected;
    }

    @Override
    public int delete(Persona entity) throws SQLException {
        this.contactar();
        int rowsAfected;
        try (PreparedStatement ps = con.prepareStatement("delete Persona where DNI = ? and Nombre = ?")) {
            ps.setString(1, entity.getDNI());
            ps.setString(2, entity.getNombre());
            rowsAfected = ps.executeUpdate();
        }
        con.close();
        return rowsAfected;
    }

    @Override
    public int delete(Long id) throws SQLException {
        this.contactar();
        int rowsAfected;
        try (PreparedStatement ps = con.prepareStatement("delete Persona where id = ?")) {
            ps.setLong(1, id);
            rowsAfected = ps.executeUpdate();
        }
        con.close();
        return rowsAfected;
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
