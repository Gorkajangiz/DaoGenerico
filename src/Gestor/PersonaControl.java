/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author edria
 */
public class PersonaControl {

    DaoPersonaClase dao = new DaoPersonaClase();
    Collection c = new ArrayList<>();

    public Collection<Persona> cogerPorNombre(String nombre) throws SQLException {
        try {
            c = dao.findByName(nombre);
            System.out.println(c.toString());
            return c;
        } catch (SQLException ex) {
            System.err.println("Ha habido el siguiente error: " + ex);
        }
    }

    public Collection<Persona> cogerPorDNI(String dni) throws SQLException {
        c = dao.findByDNI(dni);
        return c;
    }

    public Collection<Persona> cogerPorApellido(String apellido) throws SQLException {
        c = dao.findBySurname(apellido);
        return c;
    }

    public Collection<Persona> cogerPorTelefono(Integer telefono) throws SQLException {
        c = dao.findByPhone(telefono);
        return c;
    }

    public Collection<Persona> devolverTodo() throws SQLException {
        c = dao.findAll();
        return c;
    }

    public Persona cogerPorId(Long id) throws SQLException {
        Persona p = dao.findById(id);
        return p;
    }

    public void insertar(String nombre, String apellido, String dni, Integer telefono, Long id) throws SQLException {
        try {
            Persona p = new Persona(nombre, apellido, dni, telefono, id);
            dao.insert(p);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 12899) {
                throw ex;
            }
        }
    }
}
