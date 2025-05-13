/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author edria
 */
public class PersonaControl {

    DaoPersonaClase dao = new DaoPersonaClase();

    public Collection<Persona> cogerPorNombre(String nombre) throws SQLException {
        Collection<Persona> c = null;
        try {
            c = dao.findByName(nombre);
        } catch (SQLException ex) {
            throw ex;
        }
        return c;
    }

    public Persona cogerPorDNI(String dni) throws SQLException {
        try {
            return dao.findByDNI(dni).orElseThrow();
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

    public Persona cogerPorId(Long id) throws SQLException {
        try {
            return dao.findById(id).orElseThrow();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void insertar(String nombre, String apellido, String dni, Integer telefono) throws SQLException {
        try {
            Persona p = new Persona(nombre, apellido, dni, telefono, null);
            dao.insert(p);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 12899) {
                throw ex;
            }
        }
    }

    public int borrarPersona(Persona p) throws SQLException {
        try {
            return dao.delete(p);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int borrarId(Long id) throws SQLException {
        try {
            return dao.delete(id);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int editar(String nombre, String apellido, Integer telefono, String dni) throws SQLException {
        try {
            Persona p = dao.findByDNI(dni).orElseThrow();
            if (nombre.isEmpty()) {
                nombre = p.getNombre();
            }
            if (apellido.isEmpty()) {
                apellido = p.getApellido();
            }
            if (telefono == null) {
                telefono = p.getTelefono();
            }
            Persona updatedPersona = new Persona(p.getDNI(), nombre, apellido, telefono, null);

            return dao.update(updatedPersona);
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
