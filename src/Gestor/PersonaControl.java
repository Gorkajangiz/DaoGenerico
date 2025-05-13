/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author edria
 */
public class PersonaControl {

    DaoPersonaClase dao = new DaoPersonaClase();

    Collection<Persona> c = null;
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
        return dao.findByDNI(dni);
    }

    public Collection<Persona> cogerPorApellido(String apellido) throws SQLException {
        return dao.findBySurname(apellido);
    }

    public Collection<Persona> cogerPorTelefono(Integer telefono) throws SQLException {
        return dao.findByPhone(telefono);
    }

    public Collection<Persona> devolverTodo() throws SQLException {
        return dao.findAll();
    }

    public Optional<Persona> cogerPorId(Long id) throws SQLException {
        return dao.findById(id);
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

    public void borrarPersona(Persona p) {
        dao.delete(p);
    }

    public void borrarId(Long id) {
        dao.delete(id);
    }

    public void editar(String nombre, String apellido, Integer telefono, String dni, Long id) throws SQLException {
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
    }
}
