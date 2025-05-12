/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Gestor;

import java.util.Collection;

/**
 *
 * @author edria
 */
public interface Dao<T> {
    Collection<T> findAll();
    T findById(Long id);
    void insert(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(Long id);
}
