package com.sda.java9.finalproject.generics;

import java.util.List;

public interface GenericDAO <T>{
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void deleteById(Long id);
}
