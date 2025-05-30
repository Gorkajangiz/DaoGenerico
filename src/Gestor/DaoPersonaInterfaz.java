/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface DaoPersonaInterfaz extends Dao<Persona> {

    Optional<Persona> findByDNI(String DNI) throws SQLException; //Optional<Persona>

    Collection<Persona> findByName(String name) throws SQLException;

    Collection<Persona> findBySurname(String surname) throws SQLException;

    Collection<Persona> findByPhone(Integer phone) throws SQLException;
}
