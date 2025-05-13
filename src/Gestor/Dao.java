/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Gestor;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author edria
 */
public interface Dao<T> {
    Collection<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    void insert(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
}
