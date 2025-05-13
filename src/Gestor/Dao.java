/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Gestor;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author edria
 */
public interface Dao<T> {

    // Optional<T> findById(Long id) throws SQLException;
    Collection<T> findAll() throws SQLException;

    Optional<T> findById(Long id) throws SQLException;

    void insert(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(T entity) throws SQLException;

    int delete(Long id) throws SQLException;
}
