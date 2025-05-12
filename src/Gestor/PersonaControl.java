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
        Persona p = dao.findById(id);
        return p;
    }
    public void insertar(String nombre, String apellido, String dni, Integer telefono, Long id){
    }
    
    public static void main(String args []) throws SQLException{
        DaoPersonaClase dao = new DaoPersonaClase();
        dao.contactar();
        
    }
}
