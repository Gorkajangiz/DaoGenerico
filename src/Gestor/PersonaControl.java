/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author edria
 */
public class PersonaControl {

    DaoPersonaClase dao = new DaoPersonaClase();

    Collection<Persona> c = new ArrayList<>();
    Persona p;

    public Collection<Persona> cogerPorNombre(String nombre) throws SQLException {
        Collection<Persona> c = null;
        try {
            c = dao.findByName(nombre);
        } catch (SQLException ex) {
            System.err.println("Ha habido el siguiente error: " + ex);
        }
        return c;
    }

    public Optional<Persona> cogerPorDNI(String dni) throws SQLException {
        try {
            return dao.findByDNI(dni);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Collection<Persona> cogerPorApellido(String apellido) throws SQLException {
        try {
            return dao.findBySurname(apellido);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Collection<Persona> cogerPorTelefono(Integer telefono) throws SQLException {
        try {
            return dao.findByPhone(telefono);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Collection<Persona> devolverTodo() throws SQLException {
        try {
            return dao.findAll();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public Optional<Persona> cogerPorId(Long id) throws SQLException {
        try {
            return dao.findById(id);
        } catch (SQLException ex) {
            throw ex;
        }
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

    public void borrarPersona(Persona p) throws SQLException {
        try {
            dao.delete(p);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void borrarId(Long id) throws SQLException {
        try {
            dao.delete(id);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void editar(String nombre, String apellido, Integer telefono, String dni, Long id) throws SQLException {
        try {
            Optional<Persona> p = dao.findByDNI(dni);
            if (nombre.isEmpty()) {
                nombre = p.get().getNombre();
            }
            if (apellido.isEmpty()) {
                apellido = p.get().getApellido();
            }
            if (telefono == null) {
                telefono = p.get().getTelefono();
            }
            if (dni.isEmpty()) {
                dni = p.get().getDNI();
            }
            dao.update(p.orElse(new Persona(dni, nombre, apellido, telefono, null)));
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
