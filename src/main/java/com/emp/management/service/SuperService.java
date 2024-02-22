package com.emp.management.service;

import java.util.List;

public interface SuperService <T,D>{
    void save(T data);
    void update(T data);
    void delete(D id);
    T findById(D id);
    List<T> findAll();
}
