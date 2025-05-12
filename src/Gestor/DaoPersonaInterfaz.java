/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Gestor;

import Entidades.Persona;
import java.util.Collection;

/**
 *
 * @author edria
 */
public interface DaoPersonaInterfaz extends Dao<Persona>{
    Collection<Persona> findByDNI(String DNI);
    Collection<Persona> findByName(String name);
    Collection<Persona> findBySurname(String surname);
    Collection<Persona> findByPhone(Integer phone);
    void deleteByDNI(String DNI);
}
