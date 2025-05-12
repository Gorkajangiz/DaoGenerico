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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edria
 */
public class DaoPersonaClase implements DaoPersonaInterfaz{

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
        String user = "sys";
        String pass = "Enara1997";
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }
    
    

    @Override
    public Collection<Persona> findByDNI(String DNI) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Persona> findByName(String name) {
        Collection c = new ArrayList<>();
        try {
                PreparedStatement ps;
                String q1 = "select * from Persona where nombre = ?";
                this.contactar();
                ps = con.prepareStatement(q1);
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String DNI = rs.getString("DNI");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    Integer telefono = rs.getInt("telefono");
                    Persona p = new Persona(DNI, nombre, apellido, telefono);
                    c.add(p);
                }
                int r = ps.executeUpdate();
                if (r > 1) {
                    System.out.println("Hay " + r + " personas con ese nombre :(");
                }
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        return c;
    }

    @Override
    public Collection<Persona> findBySurname(String surname) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Persona> findByPhone(Integer phone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Persona> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Persona entity) {
        PreparedStatement ps = null;
        String q1 = "insert into Persona values(?, ?, ?, ?, ?)";
        try {
            this.contactar();
            ps = con.prepareStatement(q1);
            ps.setString(2, entity.getDNI());
            ps.setString(3, entity.getNombre());
            ps.setString(4, entity.getApellido());
            ps.setInt(5, entity.getTelefono());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 12899) {
                try {
                    throw ex;
                } catch (SQLException ex1) {
                    Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } else {
                System.out.println("Ha ocurrido el siguiente error: " + ex);
            }
        }
    
    }

    @Override
    public void update(Persona entity)  {
        try {
            PreparedStatement ps;
            String q1 = "update Persona set telefono = ?, nombre = ?, apellido = ? where dni = ?";
            this.contactar();
            ps = con.prepareStatement(q1);
            ps.setInt(1, entity.getTelefono());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getDNI());
            ps.setString(4, entity.getDNI());
            int r = ps.executeUpdate();
            if (r > 1) {
                throw new Exception("Hay más de un contacto con ese nombre");
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteByDNI(String DNI) {
        try {
            PreparedStatement ps;
            String q1 = "delete Persona where DNI = ?";
            this.contactar();
            ps = con.prepareStatement(q1);
            ps.setString(1, DNI);
            int r = ps.executeUpdate();
            if (r > 1) {
                throw new Exception("Hay más de un contacto con ese nombre");
            }
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPersonaClase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
