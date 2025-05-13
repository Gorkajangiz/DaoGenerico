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
    Collection<Persona> c = null;
    Persona p;
    
    public Collection<Persona> cogerPorNombre(String nombre){
        c = dao.findByName(nombre);
        return c;
    }
    public Collection<Persona> cogerPorDNI(String dni){
        c = dao.findByDNI(dni);
        return c;
    }
    public Collection<Persona> cogerPorApellido(String apellido){
        c = dao.findBySurname(apellido);
        return c;
    }
    public Collection<Persona> cogerPorTelefono(Integer telefono){
        c = dao.findByPhone(telefono);
        return c;
    }
    public Collection<Persona> devolverTodo() throws SQLException {
        c = dao.findAll();
        return c;
    }
    public Persona cogerPorId(Long id){
        p = dao.findById(id);
        return p;
    }
    public void insertar(String nombre, String apellido, String dni, Integer telefono, Long id){
        p = new Persona(nombre, apellido, dni, telefono, id);
        dao.insert(p);
    }
    public void borrarPersona(Persona p){
        dao.delete(p);
    }
    public void borrarId(Long id){
        dao.delete(id);
    }
    public void editar(String nombre, String apellido, Integer telefono, String dni, Long id) {
        p = new Persona (dni, nombre, apellido, telefono, null);
        dao.update(p);
    }
}
