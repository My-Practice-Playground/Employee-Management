package com.emp.management.service;

import java.util.List;

/**
 * GENERIC SERVICE INTERFACE FOR ALL SERVICES
 * @param <T> Entity
 * @param <D> Primary Key
 */
public interface SuperService <T,D>{
    void save(T data);
    void update(T data);
    void delete(D id);
    T findById(D id);
    List<T> findAll();
}
